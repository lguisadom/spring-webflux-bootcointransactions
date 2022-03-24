package com.nttdata.lagm.bootcointx.kafka.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaStringProducer {
	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaStringProducer.class);

    private final KafkaTemplate<String, String> kafkaTemplate;
    
    @Value("${kafka.topic.topicTransactionResult}")
    private String TOPIC_TRANSACTION_RESULT;

    public KafkaStringProducer(@Qualifier("kafkaProducerStringTemplate") KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) {
        LOGGER.info("Producing message {}", message);
        this.kafkaTemplate.send(TOPIC_TRANSACTION_RESULT, message);
    }
}
