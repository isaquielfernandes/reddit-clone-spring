package com.blueberry.service;

import java.time.Instant;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blueberry.exceptions.SpringRedditException;
import com.blueberry.model.RefreshToken;
import com.blueberry.repository.RefreshTokenRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class RefreshTokenService {

	private final RefreshTokenRepository refreshTokenRepository;
	
	public RefreshToken generateRefreshToken() {
		RefreshToken refreshToken = new RefreshToken();
		refreshToken.setToken(UUID.randomUUID().toString());
		refreshToken.setCreatedDate(Instant.now());
		return refreshTokenRepository.save(refreshToken);
	}
	
	void validateRefreshToken(String token) {
		refreshTokenRepository.findByToken(token)
			.orElseThrow(() -> new SpringRedditException("Invalid refresh Token"));
	}
	
	public void deleRefreshToken(String token) {
		refreshTokenRepository.deleteByToken(token);
	}
}
