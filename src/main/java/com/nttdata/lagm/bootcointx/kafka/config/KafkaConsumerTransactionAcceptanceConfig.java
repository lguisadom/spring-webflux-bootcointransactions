package com.nttdata.lagm.bootcointx.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.nttdata.lagm.bootcointx.kafka.message.TransactionAcceptanceMessage;

@Configuration
public class KafkaConsumerTransactionAcceptanceConfig {

//    public ConsumerFactory<String, String> transactionAcceptanceConsumerFactory() {
//        Map<String, Object> config = new HashMap<>();
//        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
//        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
//        config.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
//        /*config.put(JsonDeserializer.TYPE_MAPPINGS,
//        		"cat:com.nttdata.lagm.bootcoin.kafka.message.TransactionAcceptanceMessage, " +
//        		"hat:com.nttdata.lagm.bootcointx.kafka.message.TransactionAcceptanceMessage");*/
//        return new DefaultKafkaConsumerFactory<>(config,
//        		new StringDeserializer(), 
//        	      new JsonDeserializer<>(String.class));
//    }
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, String> 
//      kafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, String> factory =
//          new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(transactionAcceptanceConsumerFactory());
//        return factory;
//    }

//	@Bean
//    public ConsumerFactory<String, Object> consumerFactory() {
//        final JsonDeserializer<Object> jsonDeserializer = new JsonDeserializer<>();
//        jsonDeserializer.addTrustedPackages("*");
//        return new DefaultKafkaConsumerFactory<>(
//                kafkaProperties.buildConsumerProperties(), new StringDeserializer(), jsonDeserializer
//        );
//    }

	@Bean
	public ConsumerFactory<String, TransactionAcceptanceMessage> consumerFactory() {
		Map<String, Object> config = new HashMap<>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		config.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
		return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), 
				new JsonDeserializer<>(TransactionAcceptanceMessage.class, false));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, TransactionAcceptanceMessage> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, TransactionAcceptanceMessage> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());

		return factory;
	}

}
