package com.fletrax.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fletrax.model.FullVehicleData;
import com.fletrax.model.TrackingState;
import com.fletrax.serde.TrackingStateSerde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.kstream.ValueTransformerWithKey;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.kafka.streams.state.Stores;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@EnableKafkaStreams
public class VehicleTrackingKafkaStreamsService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public VehicleTrackingKafkaStreamsService(StreamsBuilder streamsBuilder) {
        System.out.println("Setting up the state store and stream processing...");

        // Define the state store without replication factor
        streamsBuilder.addStateStore(
                Stores.keyValueStoreBuilder(
                                Stores.persistentKeyValueStore("tracking-state-store"),
                                Serdes.String(),  // Key serde
                                new TrackingStateSerde()  // Use the custom serde for TrackingState
                        )
                        .withLoggingEnabled(Collections.singletonMap("retention.ms", "86400000"))  // Enable logging with 24-hour retention
        );

        // Subscribe to Kafka topic "K_unsorted_messages" and set up key-value serdes
        KStream<String, String> stream = streamsBuilder.stream(
                "K_unsorted_messages",
                Consumed.with(Serdes.String(), Serdes.String())  // Key and Value Serdes (both String in this case)
        );

        // Log incoming messages for visibility
        stream.peek((key, value) -> {
            System.out.println("Received message with key: " + key + ", value: " + value);
        });

        // Transform values and apply state tracking logic
        stream.transformValues(() -> new ValueTransformerWithKey<String, String, String>() {

                    private KeyValueStore<String, TrackingState> stateStore;

                    @Override
                    public void init(ProcessorContext context) {
                        System.out.println("Initializing the state store...");
                        stateStore = (KeyValueStore<String, TrackingState>) context.getStateStore("tracking-state-store");
                        System.out.println("State store initialized.");
                    }

                    @Override
                    public String transform(String key, String value) {
                        if (key == null) {
                            System.err.println("Received a null key. Skipping processing for this record.");
                            return null;  // Skip processing if the key is null
                        }

                        System.out.println("Processing message with key: " + key);

                        try {
                            // Deserialize the Kafka message (from K_unsorted_messages) to FullVehicleData
                            FullVehicleData vehicleData = objectMapper.readValue(value, FullVehicleData.class);
                            System.out.println("Deserialized message: " + vehicleData);

                            // Get current server timestamp and determine if the message is delayed
                            long serverTimestamp = System.currentTimeMillis() / 1000;
                            boolean isDelayed = vehicleData.getTimestamp() < (serverTimestamp - 30);
                            System.out.println("Is message delayed: " + isDelayed);

                            // Get or initialize the state for this vehicle
                            TrackingState trackingState = stateStore.get(key);
                            if (trackingState == null) {
                                trackingState = new TrackingState();
                                System.out.println("Initialized new tracking state for key: " + key);
                            } else {
                                System.out.println("Retrieved existing tracking state for key: " + key);
                            }

                            // Update counters based on delay status
                            if (!isDelayed) {
                                trackingState.incrementUndelayedCounter();
                                System.out.println("Incremented undelayed counter for key: " + key);
                            } else {
                                trackingState.resetUndelayedCounter();
                                System.out.println("Reset undelayed counter for key: " + key);
                            }
                            trackingState.incrementMessageCounter();
                            System.out.println("Incremented message counter for key: " + key);

                            // If the vehicle meets the criteria, push the message to the sorted topic
                            if (trackingState.getContinuousUndelayedMessageCounter() >= 15 &&
                                    trackingState.getMessageCounter() >= 30) {
                                System.out.println("Criteria met for key: " + key + ", pushing to sorted_messages...");
                                stateStore.put(key, trackingState);  // Update the state

                                // Reset counters after pushing to sorted_messages topic
                                trackingState.setContinuousUndelayedMessageCounter(0);  // Reset continuous undelayed counter to 0
                                trackingState.setMessageCounter(15);  // Reset message counter to 15
                                System.out.println("Reset counters after pushing to sorted_messages for key: " + key);

                                // Return the serialized message to push to sorted_messages topic
                                return objectMapper.writeValueAsString(vehicleData);
                            }

                            // Update state store with current vehicle state
                            stateStore.put(key, trackingState);
                            System.out.println("Updated tracking state for key: " + key);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        return null;  // If criteria not met, return null
                    }

                    @Override
                    public void close() {
                        // No resources to close
                    }
                }, "tracking-state-store")  // Attach state store to the transformer
                .filter((key, value) -> value != null)  // Only forward non-null messages
                .to("sorted_messages", Produced.with(Serdes.String(), Serdes.String()));  // Send to "sorted_messages" topic

        System.out.println("Stream processing setup completed.");
    }
}
