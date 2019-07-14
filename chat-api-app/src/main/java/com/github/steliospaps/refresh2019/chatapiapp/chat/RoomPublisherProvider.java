package com.github.steliospaps.refresh2019.chatapiapp.chat;

import org.reactivestreams.Publisher;

import com.github.steliospaps.refresh2019.chatapiapp.chat.db.ChatMessage;
import com.github.steliospaps.refresh2019.chatapiapp.chat.db.Room;

public interface RoomPublisherProvider {

	Publisher<Room> getNewRooms();

}
