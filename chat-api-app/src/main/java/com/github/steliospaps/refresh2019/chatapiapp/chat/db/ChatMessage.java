package com.github.steliospaps.refresh2019.chatapiapp.chat.db;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessage {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
//	private Long userId;
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
//	private Long roomId;
	@ManyToOne
	@JoinColumn(name="room_id")
	private Room room;
	private LocalDateTime ts;
	private String message;
}
