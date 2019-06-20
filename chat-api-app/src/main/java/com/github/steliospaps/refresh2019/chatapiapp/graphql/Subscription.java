package com.github.steliospaps.refresh2019.chatapiapp.graphql;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLSubscriptionResolver;
import com.github.steliospaps.refresh2019.chatapiapp.chat.ChatMessagePublisherProvider;
import com.github.steliospaps.refresh2019.chatapiapp.chat.db.ChatMessage;
import com.github.steliospaps.refresh2019.chatapiapp.graphql.dummy.DummyUpdate;
import com.github.steliospaps.refresh2019.chatapiapp.graphql.dummy.DummyUpdater;

@Component
public class Subscription implements GraphQLSubscriptionResolver {

	@Autowired
	private DummyUpdater dummyUpdater;
	
	@Autowired
	private ChatMessagePublisherProvider chatMessagePublisherProvider;
	
	public Publisher<DummyUpdate> dummyUpdates(Long roomId){
		return dummyUpdater.getPublisher(roomId);
	}
	
	public Publisher<ChatMessage> getNewChatMessages(Long roomId) {
		return chatMessagePublisherProvider.getNewChatMessagesForRoom(roomId);
	}
}
