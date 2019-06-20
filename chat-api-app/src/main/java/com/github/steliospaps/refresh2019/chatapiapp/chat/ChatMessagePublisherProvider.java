package com.github.steliospaps.refresh2019.chatapiapp.chat;

import org.reactivestreams.Publisher;

import com.github.steliospaps.refresh2019.chatapiapp.chat.db.ChatMessage;

public interface ChatMessagePublisherProvider {

	Publisher<ChatMessage> getNewChatMessagesForRoom(Long roomId);

}
