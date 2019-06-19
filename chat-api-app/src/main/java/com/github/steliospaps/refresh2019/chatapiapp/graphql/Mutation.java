package com.github.steliospaps.refresh2019.chatapiapp.graphql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.stereotype.Service;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.github.steliospaps.refresh2019.chatapiapp.chat.db.ChatMessage;
import com.github.steliospaps.refresh2019.chatapiapp.chat.db.ChatMessageRepository;
import com.github.steliospaps.refresh2019.chatapiapp.chat.db.Room;
import com.github.steliospaps.refresh2019.chatapiapp.chat.db.RoomRepository;
import com.github.steliospaps.refresh2019.chatapiapp.chat.db.User;
import com.github.steliospaps.refresh2019.chatapiapp.chat.db.UserRepository;

import lombok.SneakyThrows;

@Service
public class Mutation implements GraphQLMutationResolver {

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ChatMessageRepository chatMessageRepository;
	
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
		return chatMessageRepository
				.save(ChatMessage.builder()
						.message(message)
						.user(userRepository
								.findById(userId)
								.orElseThrow())
						.room(roomRepository
								.findById(userId)
								.orElseThrow())
						.build());
	}
}
