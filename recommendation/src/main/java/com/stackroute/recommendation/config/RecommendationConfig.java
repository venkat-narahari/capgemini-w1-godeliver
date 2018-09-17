package com.stackroute.recommendation.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.stackroute.bookservice.domain.Book;
import com.stackroute.userprofile.domain.UserProfile;

@Configuration
@EnableKafka
public class RecommendationConfig {

	@Bean
	public ConsumerFactory<String, Book> consumerFactory() {
		Map<String, Object> props = new HashMap<>();
		
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.23.239.61:9092");
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "books");
		//props.put(JsonDeserializer.TRUSTED_PACKAGES, "com.stackroute.bookservice");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(Book.class));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Book> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, Book> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}

	@Bean
	public ConsumerFactory<String, UserProfile> userConsumerFactory() {
		Map<String, Object> config = new HashMap<>();

		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.23.239.61:9092");
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_json");
		//config.put(JsonDeserializer.TRUSTED_PACKAGES, "com.stackroute.userservice");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
				new JsonDeserializer<>(UserProfile.class));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, UserProfile> userKafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, UserProfile> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(userConsumerFactory());
		return factory;
	}
}
