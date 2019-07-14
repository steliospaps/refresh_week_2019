package com.github.steliospaps.refresh2019.chatapiapp.chat;

import org.reactivestreams.Publisher;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.github.steliospaps.refresh2019.chatapiapp.chat.db.ChatMessage;
import com.github.steliospaps.refresh2019.chatapiapp.chat.db.User;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.subjects.PublishSubject;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class KafkaConsumingUserPublisherProvider implements UserPublisherProvider {

	private PublishSubject<User> subject = PublishSubject.create();
	private Flowable<User> observable= subject.serialize().share().toFlowable(BackpressureStrategy.BUFFER);

	@KafkaListener(topics = "${cluster.bus.user.topic.kafka}",
			concurrency = "1",
			groupId = "")
	public void onKafkaMessage(User chatMessage) {
		log.info("onKafkaMessage {}",chatMessage);
		subject.onNext(chatMessage);
	}
	
	@Override
	public Publisher<User> getNewUsers() {
		log.info("getNewUsers");
		return observable;
	}
}
