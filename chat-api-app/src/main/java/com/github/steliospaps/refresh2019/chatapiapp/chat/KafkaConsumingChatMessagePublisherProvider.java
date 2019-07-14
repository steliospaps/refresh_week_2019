package com.github.steliospaps.refresh2019.chatapiapp.chat;

import org.reactivestreams.Publisher;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.github.steliospaps.refresh2019.chatapiapp.chat.db.ChatMessage;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.subjects.PublishSubject;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class KafkaConsumingChatMessagePublisherProvider implements ChatMessagePublisherProvider {

	private PublishSubject<ChatMessage> subject = PublishSubject.create();
	private Flowable<ChatMessage> observable= subject.serialize().share().toFlowable(BackpressureStrategy.BUFFER);

	@KafkaListener(topics = "${cluster.bus.chat-message.topic.kafka}",
			concurrency = "1")
	public void onKafkaMessage(ChatMessage chatMessage) {
		log.info("onKafkaMessage {}",chatMessage);
		subject.onNext(chatMessage);
	}
	
	@Override
	public Publisher<ChatMessage> getNewChatMessagesForRoom(Long roomId) {
		log.info("getNewChatMessagesForRoom {}",roomId);
		return observable.filter(m -> m.getRoom().getId().equals(roomId));
	}
}
