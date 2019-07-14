package com.github.steliospaps.refresh2019.chatapiapp.chat;

import org.reactivestreams.Publisher;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.github.steliospaps.refresh2019.chatapiapp.chat.db.ChatMessage;
import com.github.steliospaps.refresh2019.chatapiapp.chat.db.Room;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.subjects.PublishSubject;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class KafkaConsumingRoomPublisherProvider implements RoomPublisherProvider {

	private PublishSubject<Room> subject = PublishSubject.create();
	private Flowable<Room> observable= subject.serialize().share().toFlowable(BackpressureStrategy.BUFFER);

	@KafkaListener(topics = "${cluster.bus.room.topic.kafka}",
			concurrency = "1")
	public void onKafkaMessage(Room chatMessage) {
		log.info("onKafkaMessage {}",chatMessage);
		subject.onNext(chatMessage);
	}
	
	@Override
	public Publisher<Room> getNewRooms() {
		log.info("getNewRooms");
		return observable;
	}
}
