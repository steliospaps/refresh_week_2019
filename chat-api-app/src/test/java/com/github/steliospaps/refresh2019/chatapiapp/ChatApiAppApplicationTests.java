package com.github.steliospaps.refresh2019.chatapiapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration-test")
@EmbeddedKafka(topics = { "${cluster.bus.chat-message.topic.kafka}", "${cluster.bus.room.topic.kafka}",
		"${cluster.bus.user.topic.kafka}" }, brokerProperties = { "log.dir=target/out/embedded-kafka" })
public class ChatApiAppApplicationTests {

	@Test
	public void contextLoads() {
	}

}
