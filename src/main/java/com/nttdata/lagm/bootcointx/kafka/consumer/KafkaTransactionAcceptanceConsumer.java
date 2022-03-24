package com.nttdata.lagm.bootcointx.kafka.consumer;

import java.util.stream.StreamSupport;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Headers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.nttdata.lagm.bootcointx.model.TransactionAcceptance;
import com.nttdata.lagm.bootcointx.model.service.TransactionService;

@Component
public class KafkaTransactionAcceptanceConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaTransactionAcceptanceConsumer.class);

    @Autowired
    private TransactionService transactionService;
    
//    private final KafkaTemplate<String, TransactionAcceptance> kafkaTemplate;
//
//    public KafkaTransactionAcceptanceConsumer(@Qualifier("kafkaTransactionAcceptanceTemplate") KafkaTemplate<String, TransactionAcceptance> kafkaTemplate) {
//        this.kafkaTemplate = kafkaTemplate;
//    }
//
//    public void sendMessage(TransactionAcceptance transactionAcceptance) {
//        LOGGER.info("Producing message {}", transactionAcceptance);
//        this.kafkaTemplate.send("TOPIC-Transaction-Acceptance", transactionAcceptance);
//    }

    @KafkaListener(
		topics = "TOPIC-Transaction-Acceptance", 
		groupId = "bootcamp2022",
		containerFactory = "kafkaListenerContainerFactory")
    public void consume(ConsumerRecord<String, TransactionAcceptance> cr,
            @Payload TransactionAcceptance payload) {
    	LOGGER.info("Consume [JSON] received Payload: {} ", payload);
    	transactionService.create(payload).subscribe();
    }
}