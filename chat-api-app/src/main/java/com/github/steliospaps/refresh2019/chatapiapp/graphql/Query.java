package com.github.steliospaps.refresh2019.chatapiapp.graphql;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.github.steliospaps.refresh2019.chatapiapp.chat.db.Room;
import com.github.steliospaps.refresh2019.chatapiapp.chat.db.RoomRepository;
import com.github.steliospaps.refresh2019.chatapiapp.chat.db.User;
import com.github.steliospaps.refresh2019.chatapiapp.chat.db.UserRepository;

@Service
public class Query implements GraphQLQueryResolver{

	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public Iterable<Room> getAllRooms() {
		return roomRepository.findAll();
	}	

	public Optional<Room> getRoom(Long id) {
		return roomRepository.findById(id);
	}	

	
	public Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}	
}
