package com.github.steliospaps.refresh2019.chatapiapp.graphql;

import org.springframework.stereotype.Service;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.github.steliospaps.refresh2019.chatapiapp.chat.db.Room;
import com.github.steliospaps.refresh2019.chatapiapp.chat.db.RoomRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class Query implements GraphQLQueryResolver{

	private final RoomRepository roomRepository;
	
	public Iterable<Room> getAllRooms() {
		return roomRepository.findAll();
	}	
}
