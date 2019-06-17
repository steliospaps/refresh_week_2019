package com.github.steliospaps.refresh2019.chatapiapp.rest;

import com.fasterxml.jackson.annotation.JsonCreator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageCreationDto {
	private Long userId;
	private Long roomId;
	private String message;

}
