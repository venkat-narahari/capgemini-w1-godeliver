package com.stackroute.orderdetails.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.stackroute.orderdetails.domain.Orderdetails;

@Configuration
@EnableKafka
public class KafkaConfig {
	private static final String TOPIC = "order_details";

	@Bean
	public ProducerFactory<String, Orderdetails> producerFactory() {
		Map<String, Object> props = new HashMap<>();

		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "details");
		props.put(JsonDeserializer.TRUSTED_PACKAGES, "com.stackroute.orderdetails");
		return new DefaultKafkaProducerFactory<>(props);
	}

	@Bean
	public KafkaTemplate<String, Orderdetails> kafkaTemplate() {
		return new KafkaTemplate<String, Orderdetails>(producerFactory());
	}

	public static String getTopic() {
		return TOPIC;
	}
}
