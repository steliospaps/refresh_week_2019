package com.github.steliospaps.refresh2019.chatapiapp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.steliospaps.refresh2019.chatapiapp.chat.db.ChatMessage;
import com.github.steliospaps.refresh2019.chatapiapp.chat.db.ChatMessageRepository;
import com.github.steliospaps.refresh2019.chatapiapp.chat.db.Room;
import com.github.steliospaps.refresh2019.chatapiapp.chat.db.RoomRepository;
import com.github.steliospaps.refresh2019.chatapiapp.chat.db.User;
import com.github.steliospaps.refresh2019.chatapiapp.chat.db.UserRepository;

@Controller
public class InsertingController {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private ChatMessageRepository chatMessageRepository;
	
	@RequestMapping(path = "/user", method = RequestMethod.PUT)
	public @ResponseBody Long insertUser(@RequestBody UserCreationDto user) {
		return userRepository.save(
				User
					.builder()
					.name(user.getName())
					.build()).getId();
	}

	@PutMapping(path = "/room")
	public @ResponseBody Long insertRoom(@RequestBody RoomCreationDto room) {
		return roomRepository.save(
				Room
					.builder()
					.name(room.getName())
					.build()).getId();
	}

	@PutMapping(path = "/message")
	public @ResponseBody void insertMessage(@RequestBody ChatMessageCreationDto message) {
		chatMessageRepository.save(
				ChatMessage
					.builder()
					.room(roomRepository.findById(message.getRoomId()).get())
					.user(userRepository.findById(message.getUserId()).get())
					.message(message.getMessage())
					.build());
	}
}
