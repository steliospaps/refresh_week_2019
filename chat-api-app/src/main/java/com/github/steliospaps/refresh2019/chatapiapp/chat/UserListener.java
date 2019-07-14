package com.github.steliospaps.refresh2019.chatapiapp.chat;

import com.github.steliospaps.refresh2019.chatapiapp.chat.db.User;

public interface UserListener {
	void onNewUser(User user);
}
