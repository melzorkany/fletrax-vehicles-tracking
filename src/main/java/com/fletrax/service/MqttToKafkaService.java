package com.fletrax.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fletrax.model.FullVehicleData;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MqttToKafkaService {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public MqttToKafkaService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = new ObjectMapper();
    }

    // Process incoming MQTT message and forward it to Kafka topic "K_unsorted_messages"
    public void processIncomingMqttMessage(String payload) {
        try {
            // Parse the incoming MQTT message as JSON
            FullVehicleData vehicleData = objectMapper.readValue(payload, FullVehicleData.class);

            // Use the 'ident' field as the key for Kafka
            String key = String.valueOf(vehicleData.getIdent());

            // Forward the data to Kafka topic "K_unsorted_messages" with 'ident' as key
            kafkaTemplate.send("K_unsorted_messages", key, vehicleData);
            System.out.println("Forwarded MQTT message to Kafka topic 'K_unsorted_messages' with key: " + key);

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to process MQTT message: " + payload);
        }
    }
}


