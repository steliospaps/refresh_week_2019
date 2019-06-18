package com.github.steliospaps.refresh2019.chatapiapp.graphql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.github.steliospaps.refresh2019.chatapiapp.chat.db.ChatMessage;
import com.github.steliospaps.refresh2019.chatapiapp.chat.db.ChatMessageRepository;
import com.github.steliospaps.refresh2019.chatapiapp.chat.db.Room;

@Service
public class RoomResolver implements GraphQLResolver<Room> {
	@Autowired
	private ChatMessageRepository chatMessageRepository;
	
	public Iterable<ChatMessage> getChatMessages(Room room) {
		//TODO make count and argument
		return chatMessageRepository.findMostRecent(room.getId(), 1000);
	}
}
