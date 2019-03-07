package at.jugger.tracker.service.impl;

import at.jugger.tracker.domain.LoginTokenEntity;
import at.jugger.tracker.dto.User;
import at.jugger.tracker.exceptions.TokenAlreadyUsedException;
import at.jugger.tracker.exceptions.TokenExpiredException;
import at.jugger.tracker.exceptions.TokenNotFoundException;
import at.jugger.tracker.mapper.LoginTokenMapper;
import at.jugger.tracker.mapper.UserMapper;
import at.jugger.tracker.repository.LoginTokenRepository;
import at.jugger.tracker.service.AuthenticationService;
import at.jugger.tracker.service.dto.LoginToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private static final int TOKEN_VALID_FOR_X_MINUTES = 30;

    private final LoginTokenRepository loginTokenRepository;
    private final UserMapper userMapper;
    private final LoginTokenMapper loginTokenMapper;

    AuthenticationServiceImpl(
            LoginTokenRepository loginTokenRepository, UserMapper userMapper, LoginTokenMapper loginTokenMapper
    ) {
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
    public void authenticate(
            String token
    ) throws TokenNotFoundException, TokenAlreadyUsedException, TokenExpiredException {
        LoginTokenEntity loginToken = loginTokenRepository.findByToken(token);
        TokenState tokenState = getTokenState(loginToken);

        switch (tokenState) {
            case NO_TOKEN:
                throw new TokenNotFoundException(token);
            case USED:
                throw new TokenAlreadyUsedException(loginTokenMapper.toDto(loginToken));
            case EXPIRED:
                throw new TokenExpiredException(loginTokenMapper.toDto(loginToken));
            case VALID:
                createSession(loginToken);
                useToken(loginToken);
        }
    }

    @Override
    public void logout() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }

    private void createSession(LoginTokenEntity loginTokenEntity) {
        UsernamePasswordAuthenticationToken springToken = new UsernamePasswordAuthenticationToken(
                loginTokenEntity.getUser().getEmail(), "", Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + loginTokenEntity.getUser().getRole())));
        SecurityContextHolder.getContext().setAuthentication(springToken);
        springToken.setDetails(userMapper.toDto(loginTokenEntity.getUser()));
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
