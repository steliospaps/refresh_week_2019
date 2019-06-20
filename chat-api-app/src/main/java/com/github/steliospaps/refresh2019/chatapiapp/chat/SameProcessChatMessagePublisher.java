package com.github.steliospaps.refresh2019.chatapiapp.chat;

import org.reactivestreams.Publisher;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.github.steliospaps.refresh2019.chatapiapp.chat.db.ChatMessage;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.subjects.PublishSubject;
import lombok.extern.slf4j.Slf4j;

@Component
@Profile({"dev","integration-test"})
@Slf4j
public class SameProcessChatMessagePublisher implements ChatMessageListener, ChatMessagePublisherProvider {
	private PublishSubject<ChatMessage> subject = PublishSubject.create();
	private Flowable<ChatMessage> observable= subject.serialize().share().toFlowable(BackpressureStrategy.BUFFER);

	@Override
	public void onNewChatMessage(ChatMessage chatMessage) {
		log.info("onNewChatMessage {}",chatMessage);
		subject.onNext(chatMessage);
	}

	@Override
	public Publisher<ChatMessage> getNewChatMessagesForRoom(Long roomId) {
		log.info("getNewChatMessagesForRoom {}",roomId);
		return observable.filter(m -> m.getRoom().getId().equals(roomId));
	}
}
