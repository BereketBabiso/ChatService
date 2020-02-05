package com.chat.simple.util;

import java.security.MessageDigest;
import java.util.UUID;

import org.springframework.stereotype.Component;

import lombok.SneakyThrows;

@Component
public class UuidGeneratory {
	
	@SneakyThrows
	public String generateUuid(String input) {
		if(input!=null && !input.trim().isEmpty()) {
			byte[] inputInByte = input.getBytes("UTF-8");
			//UUID uuid = UUID.nameUUIDFromBytes(inputInByte);
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			byte[] disgested = md.digest(inputInByte);
			
			String guid = UUID.nameUUIDFromBytes(disgested).toString();
			return guid;
		}
		return null;
	}

}
