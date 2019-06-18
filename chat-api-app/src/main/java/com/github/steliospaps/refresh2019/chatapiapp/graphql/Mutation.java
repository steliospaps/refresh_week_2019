package com.github.steliospaps.refresh2019.chatapiapp.graphql;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.github.steliospaps.refresh2019.chatapiapp.chat.db.Room;
import com.github.steliospaps.refresh2019.chatapiapp.chat.db.RoomRepository;

@Service
public class Mutation implements GraphQLMutationResolver {

	@Autowired
	private RoomRepository roomRepository;
	
	public Room createRoom(String name) {
		return roomRepository
				.save(Room.builder()
						.name(name)
						.build());
	}
}
