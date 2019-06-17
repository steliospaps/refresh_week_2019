package com.github.steliospaps.refresh2019.chatapiapp.chat.db;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaTest {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoomRepository roomRepository;
	@Autowired
	private ChatMessageRepository chatMessageRepository;
	
	@Before
	public void cleanup() {
		userRepository.deleteAll();
	}
	
	@Test
	public void testUserRepository() { 
		User user = User.builder().name("foo").build();
		User user2 = userRepository.save(user);
		assertNotNull(user2.getId());
		assertEquals("foo",
				userRepository
					.findById(user2.getId())
					.map(i -> i.getName())
					.orElse("nothing")
					);
		
	}

	@Test
	public void testRoomRepository() { 
		Room user = Room.builder().name("foo").build();
		Room user2 = roomRepository.save(user);
		assertNotNull(user2.getId());
		assertEquals("foo",
				roomRepository
					.findById(user2.getId())
					.map(i -> i.getName())
					.orElse("nothing")
					);
		
	}

	@Test
	public void testChatMessageRepository() throws Exception {
		User user = userRepository.save(User.builder().name("userName").build());
		Room room = roomRepository.save(Room.builder().name("roomName").build());
		Long id = chatMessageRepository.save(
				ChatMessage.builder()
					.message("hello world")
					.room(room)
					.user(user)
					.build()
				).getId();
		
		ChatMessage chatMessage = chatMessageRepository.findById(id).get();
		assertThat(chatMessage.getMessage(), equalTo("hello world"));
		assertThat(chatMessage.getRoom().getName(), equalTo("roomName"));
		assertThat(chatMessage.getUser().getName(), equalTo("userName"));
		assertTrue(chatMessage.getTs().isBefore(LocalDateTime.now()));
		assertTrue(chatMessage.getTs().plusMinutes(1).isAfter(LocalDateTime.now()));
		
	}
	
}
