package com.rently.rentlyAPI.services.auth;

import com.rently.rentlyAPI.entity.User;
import com.rently.rentlyAPI.entity.auth.AccessToken;
import com.rently.rentlyAPI.entity.auth.RefreshToken;
import com.rently.rentlyAPI.entity.auth.enums.TokenType;
import com.rently.rentlyAPI.repository.auth.RefreshTokenRepository;
import com.rently.rentlyAPI.repository.auth.TokenRepository;
import com.rently.rentlyAPI.security.utils.JwtUtils;
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

