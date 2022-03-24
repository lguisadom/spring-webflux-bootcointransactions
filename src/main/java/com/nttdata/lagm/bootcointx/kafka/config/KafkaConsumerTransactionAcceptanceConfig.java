package com.nttdata.lagm.bootcointx.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.nttdata.lagm.bootcointx.model.TransactionAcceptance;

@Configuration
public class KafkaConsumerTransactionAcceptanceConfig {

	@Value("${kafka.uri}")
	private String KAFKA_URI;

	@Bean
	public ConsumerFactory<String, TransactionAcceptance> consumerFactory() {
		Map<String, Object> config = new HashMap<>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_URI);
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		config.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
		return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), 
				new JsonDeserializer<>(TransactionAcceptance.class, false));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, TransactionAcceptance> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, TransactionAcceptance> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());

		return factory;
	}

}
