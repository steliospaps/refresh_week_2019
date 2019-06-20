package com.github.steliospaps.refresh2019.chatapiapp.graphql;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.github.steliospaps.refresh2019.chatapiapp.chat.ChatMessageListener;
import com.github.steliospaps.refresh2019.chatapiapp.chat.db.ChatMessage;
import com.github.steliospaps.refresh2019.chatapiapp.chat.db.ChatMessageRepository;
import com.github.steliospaps.refresh2019.chatapiapp.chat.db.Room;
import com.github.steliospaps.refresh2019.chatapiapp.chat.db.RoomRepository;
import com.github.steliospaps.refresh2019.chatapiapp.chat.db.User;
import com.github.steliospaps.refresh2019.chatapiapp.chat.db.UserRepository;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Mutation implements GraphQLMutationResolver,InitializingBean {

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ChatMessageRepository chatMessageRepository;
	
	@Autowired(required = false)
	private ChatMessageListener[] chatMessageListeners = new ChatMessageListener[]{};
	
	
	
	public Room createRoom(String name) {
		return roomRepository
				.save(Room.builder()
						.name(name)
						.build());
	}

	public User createUser(String name) {
		return userRepository
				.save(User.builder()
						.name(name)
						.build());
	}

	@SneakyThrows
	public ChatMessage createChatMessage(Long userId,Long roomId,String message) {
		//Thread.sleep(5000L); //uncomment to test latency
		//if(true) throw new RuntimeException("error"); //uncomment to test errors
		ChatMessage chatMessage = chatMessageRepository
				.save(ChatMessage.builder()
						.message(message)
						.user(userRepository
								.findById(userId)
								.orElseThrow())
						.room(roomRepository
								.findById(userId)
								.orElseThrow())
						.build());
		updateListeners(chatMessage);
		return chatMessage;
	}

	private void updateListeners(ChatMessage chatMessage) {
		for(ChatMessageListener l: chatMessageListeners) {
			try {
				l.onNewChatMessage(chatMessage);
			}catch(Exception e) {
				log.error("while publishing message "+chatMessage+" will ignore and continue",e);
			}
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("{} {} beans defined. ClassNames: {}",
				chatMessageListeners.length,
				ChatMessageListener.class.getSimpleName(),
				Arrays.asList(chatMessageListeners).stream()
					.map(i -> i.getClass().getSimpleName())
					.collect(Collectors.joining(","))
					);
	}
}
