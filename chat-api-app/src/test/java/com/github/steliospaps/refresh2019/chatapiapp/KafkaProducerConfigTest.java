package com.github.steliospaps.refresh2019.chatapiapp;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.test.context.ActiveProfiles;

@EnableKafka
@TestConfiguration
@ActiveProfiles("integration-test")
public class KafkaProducerConfigTest {

	@Bean
	public EmbeddedKafkaBroker embeddedKafkaBroker() {
		return new EmbeddedKafkaBroker(1, false, 2, "test-events");
	}
}