package com.rently.rentlyAPI.auth.service;

import com.rently.rentlyAPI.domain.entity.User;
import com.rently.rentlyAPI.auth.domain.entity.token.AccessToken;
import com.rently.rentlyAPI.auth.domain.entity.token.RefreshToken;
import com.rently.rentlyAPI.auth.domain.enums.TokenType;
import com.rently.rentlyAPI.auth.repository.RefreshTokenRepository;
import com.rently.rentlyAPI.auth.repository.TokenRepository;
import com.rently.rentlyAPI.auth.utils.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TokenService {

	private final TokenRepository tokenRepository;
	private final RefreshTokenRepository refreshTokenRepository;
	private final JwtUtils jwtUtils;
	

	public void revokeAllUserTokens(User user) {
		tokenRepository.findAllValidTokenByUser(user.getId()).forEach(token -> {
			token.setExpired(true);
			token.setRevoked(true);
		});
	}

	public void revokeAllUserRefreshTokens(User user) {
		refreshTokenRepository.findAllValidRefreshTokenByUser(user.getId()).forEach(token -> {
			token.setExpired(true);
			token.setRevoked(true);
		});
	}

	public void saveUserTokens(User user, String jwtToken, String refreshToken) {
		AccessToken accessTokenEntity = AccessToken.builder()
				.user(user)
				.token(jwtToken)
				.tokenType(TokenType.BEARER)
				.expired(false)
				.revoked(false)
				.build();

		RefreshToken refreshTokenEntity = RefreshToken.builder()
				.accessToken(accessTokenEntity)
				.refreshToken(refreshToken)
				.tokenType(TokenType.BEARER)
				.expired(false)
				.revoked(false)
				.build();

		tokenRepository.save(accessTokenEntity);
		refreshTokenRepository.save(refreshTokenEntity);
	}
	
}

