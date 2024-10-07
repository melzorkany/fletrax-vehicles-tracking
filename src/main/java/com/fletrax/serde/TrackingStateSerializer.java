package com.fletrax.serde;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fletrax.model.TrackingState;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class TrackingStateSerializer implements Serializer<TrackingState>, Deserializer<TrackingState> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(String topic, TrackingState data) {
        try {
            return objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            throw new RuntimeException("Error serializing TrackingState", e);
        }
    }

    @Override
    public TrackingState deserialize(String topic, byte[] data) {
        try {
            return objectMapper.readValue(data, TrackingState.class);
        } catch (Exception e) {
            throw new RuntimeException("Error deserializing TrackingState", e);
        }
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // No configuration needed
    }

    @Override
    public void close() {
        // Nothing to close
    }
}
