package com.github.steliospaps.refresh2019.chatapiapp.chat;

import com.github.steliospaps.refresh2019.chatapiapp.chat.db.Room;

public interface RoomListener {
	void onNewRoom(Room room);
}
