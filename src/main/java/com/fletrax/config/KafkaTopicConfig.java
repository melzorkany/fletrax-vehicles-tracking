package com.fletrax.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.stereotype.Component;

@Component
public class KafkaTopicConfig {

    // Create the topic "K_unsorted_messages" with 3 partitions and replication factor 1
    @Bean
    public NewTopic unsortedMessagesTopic() {
        return TopicBuilder.name("K_unsorted_messages")
                .partitions(1)
                .replicas(1)
                .build();
    }

    // Create the topic "sorted_messages" with 3 partitions and replication factor 1
    @Bean
    public NewTopic sortedMessagesTopic() {
        return TopicBuilder.name("sorted_messages")
                .partitions(1)
                .replicas(1)
                .build();
    }
}
