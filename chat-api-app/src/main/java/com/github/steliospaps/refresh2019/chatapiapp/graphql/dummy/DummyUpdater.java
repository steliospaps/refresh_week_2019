package com.github.steliospaps.refresh2019.chatapiapp.graphql.dummy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.observables.ConnectableObservable;
import lombok.extern.slf4j.Slf4j;

/**
 * see https://github.com/graphql-java-kickstart/graphql-spring-boot/blob/master/example-graphql-subscription/src/main/java/com/oembedler/moon/graphql/boot/publishers/StockTickerPublisher.java
 * @param roomId
 * @return
 */
@Component
@Slf4j
public class DummyUpdater {

	private final Flowable<DummyUpdate> publisher;
	{
		ConnectableObservable<DummyUpdate> observable = Observable.<DummyUpdate>create(
			emitter -> {
				ScheduledExecutorService executorService = 
						Executors.newSingleThreadScheduledExecutor(
								r -> {
									Thread t = new Thread(r);
									t.setDaemon(true);
									t.setName("DummyUpdaterThread");
									return t;
								});
				executorService.scheduleAtFixedRate(
						() -> sendDummyUpdate(emitter)
						, 0, 5, TimeUnit.SECONDS);
			}
				)
			.share()
			.publish();
			observable.connect();
			publisher = observable.toFlowable(BackpressureStrategy.BUFFER);
	}

	public Flowable<DummyUpdate> getPublisher(Long roomId) {
		log.info("getPublisher {}",roomId);
		return publisher.map(i -> new DummyUpdate(roomId + " "+i.getDateTime()));
	}

	private void sendDummyUpdate(ObservableEmitter<DummyUpdate> emitter) {
		try {
			String now = LocalDateTime.now().toString();
			log.info("sending dummy update {}",now);
			emitter.onNext(new DummyUpdate(now));
		} catch (Exception e) {
			log.error("while sending update. Will skip and continue",e);
		}
	}

}
