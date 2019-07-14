package com.github.steliospaps.refresh2019.chatapiapp.chat.db;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration-test")
@EmbeddedKafka(topics = { "${cluster.bus.chat-message.topic.kafka}", "${cluster.bus.room.topic.kafka}",
		"${cluster.bus.user.topic.kafka}" }, brokerProperties = { "log.dir=target/out/embedded-kafka" })
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
		roomRepository.deleteAll();
		chatMessageRepository.deleteAll();
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
	
	@Test
	public void testFetchLastXmessages() throws Exception {
		User user = userRepository.save(User.builder().name("userName").build());
		Room room = roomRepository.save(Room.builder().name("roomName").build());
		insertMessage(room,user,"msg1");
		insertMessage(room,user,"msg2");
		insertMessage(room,user,"msg3");
		
		List<ChatMessage> messages = chatMessageRepository.findMostRecent(room.getId(),2);
		assertThat(messages.size(),equalTo(2));
		assertThat(messages.get(0).getMessage(),equalTo("msg3"));
		assertThat(messages.get(1).getMessage(),equalTo("msg2"));
	}

	private void insertMessage(Room room, User user, String text) {
		chatMessageRepository.save(
				ChatMessage.builder()
					.message(text)
					.room(room)
					.user(user)
					.build()
				);
	}
}
