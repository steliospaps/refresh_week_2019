package com.github.steliospaps.refresh2019.chatapiapp.graphql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.github.steliospaps.refresh2019.chatapiapp.chat.db.Room;
import com.github.steliospaps.refresh2019.chatapiapp.chat.db.RoomRepository;
import com.github.steliospaps.refresh2019.chatapiapp.chat.db.User;
import com.github.steliospaps.refresh2019.chatapiapp.chat.db.UserRepository;

@Service
public class Mutation implements GraphQLMutationResolver {

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private UserRepository userRepository;
	
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
}
