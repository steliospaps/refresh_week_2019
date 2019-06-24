package com.github.steliospaps.refresh2019.chatapiapp.graphql;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.github.steliospaps.refresh2019.chatapiapp.chat.db.ChatMessage;
import com.github.steliospaps.refresh2019.chatapiapp.chat.db.ChatMessageRepository;
import com.github.steliospaps.refresh2019.chatapiapp.chat.db.Room;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RoomResolver implements GraphQLResolver<Room> {
	@Autowired
	private ChatMessageRepository chatMessageRepository;

	private Random random = new Random();

	public Iterable<ChatMessage> getChatMessages(Room room) {
		// TODO make count and argument
		return chatMessageRepository.findMostRecent(room.getId(), 1000);
	}

	/**
	 * expensive call that should be deferred
	 * 
	 * @return
	 */
	public CompletableFuture<Double> getRelevance(Room room) {
		return CompletableFuture.supplyAsync(() -> {
			try {
				log.info("getRelevance called for {}", room.getId());
				Thread.sleep(2000L + random.nextInt(1000));
				// TODO make this user related
				double res = random.nextDouble();
				log.info("getRelevance finished for {}", room.getId());
				return res;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
	}
}
