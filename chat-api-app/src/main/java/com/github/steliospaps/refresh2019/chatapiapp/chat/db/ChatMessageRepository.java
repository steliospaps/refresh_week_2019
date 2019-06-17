package com.github.steliospaps.refresh2019.chatapiapp.chat.db;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ChatMessageRepository extends CrudRepository<ChatMessage, Long>{
	@Query(value = "select * from chat_message m where m.room_id = :roomId order by id desc limit :count",
			nativeQuery = true)
	List<ChatMessage> findMostRecent(@Param("roomId") Long roomId, @Param("count") int count);

}
