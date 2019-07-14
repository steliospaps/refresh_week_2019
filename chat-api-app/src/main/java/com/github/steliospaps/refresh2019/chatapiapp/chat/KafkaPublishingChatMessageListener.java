package com.github.steliospaps.refresh2019.chatapiapp.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.github.steliospaps.refresh2019.chatapiapp.chat.db.ChatMessage;
import com.github.steliospaps.refresh2019.chatapiapp.chat.db.Room;
import com.github.steliospaps.refresh2019.chatapiapp.chat.db.User;

import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class KafkaPublishingChatMessageListener implements ChatMessageListener, RoomListener, UserListener {

	@Value("${cluster.bus.chat-message.topic.kafka}")
	private String chatTopic;

	@Value("${cluster.bus.user.topic.kafka}")
	private String userTopic;

	@Value("${cluster.bus.room.topic.kafka}")
	private String roomTopic;
	
	@Autowired
	private KafkaTemplate<Long,Object> kafkaTemplate;
	
	@Override
	public void onNewChatMessage(ChatMessage chatMessage) {
		log.info("sending to {} {}",chatTopic,chatMessage);
		kafkaTemplate.send(chatTopic, chatMessage.getRoom().getId(), chatMessage);																																																																																																																																																																																																																			
	}

	@Override
	public void onNewUser(User user) {
		log.info("sending to {} {}",userTopic,user);
		kafkaTemplate.send(userTopic, user.getId(), user);																																																																																																																																																																																																																			
		
	}

	@Override
	public void onNewRoom(Room room) {
		log.info("sending to {} {}",roomTopic,room);
		kafkaTemplate.send(roomTopic, room.getId(), room);																																																																																																																																																																																																																			
		
	}

}
