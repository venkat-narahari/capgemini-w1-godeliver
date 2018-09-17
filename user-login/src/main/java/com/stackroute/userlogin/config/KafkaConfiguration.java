package com.stackroute.userlogin.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import com.stackroute.userprofile.domain.UserProfile;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
@PropertySource(value = {"classpath:bootstrap.yml"})
public class KafkaConfiguration {
	
	@Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

	/* kafka consumer factory*/
	@Bean
	public ConsumerFactory<String, UserProfile> consumerFactory() {
		Map<String, Object> config = new HashMap<>();
		
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_json");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
				new JsonDeserializer<>(UserProfile.class));
	}

	/* kafka listner */
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, UserProfile> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, UserProfile> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}

}
