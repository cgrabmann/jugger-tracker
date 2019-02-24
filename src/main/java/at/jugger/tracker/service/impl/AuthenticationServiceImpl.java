package at.jugger.tracker.service.impl;

import at.jugger.tracker.domain.LoginTokenEntity;
import at.jugger.tracker.dto.User;
import at.jugger.tracker.mapper.LoginTokenMapper;
import at.jugger.tracker.mapper.UserMapper;
import at.jugger.tracker.repository.LoginTokenRepository;
import at.jugger.tracker.service.AuthenticationService;
import at.jugger.tracker.service.dto.LoginToken;
import at.jugger.tracker.service.exceptions.NoTokenException;
import at.jugger.tracker.service.exceptions.TokenAlreadyUsedException;
import at.jugger.tracker.service.exceptions.TokenExpiredException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private static final int TOKEN_VALID_FOR_X_MINUTES = 30;

    private final LoginTokenRepository loginTokenRepository;
    private final UserMapper userMapper;
    private final LoginTokenMapper loginTokenMapper;

    AuthenticationServiceImpl(LoginTokenRepository loginTokenRepository, UserMapper userMapper, LoginTokenMapper loginTokenMapper) {
        this.loginTokenRepository = loginTokenRepository;
        this.userMapper = userMapper;
        this.loginTokenMapper = loginTokenMapper;
    }

    @Override
    public LoginToken createLoginToken(User user) {
        LoginTokenEntity loginToken = loginTokenRepository.findByUser_UserId(user.getId());
        if (loginToken == null) {
            loginToken = new LoginTokenEntity();
        }

        loginToken.setUsed(false);
        loginToken.setUser(userMapper.toEntity(user));
        loginToken.setExpiryDate(LocalDateTime.now().plusMinutes(TOKEN_VALID_FOR_X_MINUTES));
        loginToken.setToken(generateTokenId());

        loginTokenRepository.save(loginToken);

        return loginTokenMapper.toDto(loginToken);
    }

    private String generateTokenId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    @Override
    public void authenticate(String tokenId) throws NoTokenException, TokenAlreadyUsedException, TokenExpiredException {
        LoginTokenEntity loginToken = loginTokenRepository.findByTokenId(tokenId);
        TokenState tokenState = getTokenState(loginToken);

        switch (tokenState) {
            case NO_TOKEN:
                throw new NoTokenException();
            case USED:
                throw new TokenAlreadyUsedException(loginToken);
            case EXPIRED:
                throw new TokenExpiredException(loginToken);
            case VALID:
                createSession();
                useToken(loginToken);
        }
    }

    private void createSession() {
        // TODO Implement me
    }

    private void useToken(LoginTokenEntity loginTokenEntity) {
        loginTokenEntity.setUsed(true);
        loginTokenRepository.save(loginTokenEntity);
    }

    private TokenState getTokenState(LoginTokenEntity loginTokenEntity) {
        if (loginTokenEntity == null) {
            return TokenState.NO_TOKEN;
        } else if (loginTokenEntity.isUsed()) {
            return TokenState.USED;
        } else if (loginTokenEntity.getExpiryDate().isBefore(LocalDateTime.now())) {
            return TokenState.EXPIRED;
        } else {
            return TokenState.VALID;
        }
    }

    private enum TokenState {
        NO_TOKEN,
        VALID,
        USED,
        EXPIRED
    }
}
