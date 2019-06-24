package com.github.steliospaps.refresh2019.chatapiapp.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.github.steliospaps.refresh2019.chatapiapp.chat.db.ChatMessage;

import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
@Profile({"!integration-test"})//TODO add kafka in integration test
public class KafkaPublishingChatMessageListener implements ChatMessageListener {

	@Value("${cluster.bus.chat-message.topic.kafka}")
	private String topic;
	
	@Autowired
	private KafkaTemplate<Long,ChatMessage> kafkaTemplate;
	
	@Override
	public void onNewChatMessage(ChatMessage chatMessage) {
		log.info("sending to {} {}",topic,chatMessage);
		kafkaTemplate.send(topic, chatMessage.getRoom().getId(), chatMessage);																																																																																																																																																																																																																			
	}

}
