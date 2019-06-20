package com.github.steliospaps.refresh2019.chatapiapp.chat;

import com.github.steliospaps.refresh2019.chatapiapp.chat.db.ChatMessage;

public interface ChatMessageListener {

	void onNewChatMessage(ChatMessage chatMessage);

}
