package com.github.steliospaps.refresh2019.chatapiapp.graphql;

import java.util.Arrays;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLSubscriptionResolver;
import com.github.steliospaps.refresh2019.chatapiapp.graphql.dummy.DummyUpdate;
import com.github.steliospaps.refresh2019.chatapiapp.graphql.dummy.DummyUpdater;

@Component
public class Subscription implements GraphQLSubscriptionResolver {

	@Autowired
	private DummyUpdater dummyUpdater;
	
	public Publisher<DummyUpdate> dummyUpdates(Long roomId){
		return dummyUpdater.getPublisher(roomId);
	}
}
