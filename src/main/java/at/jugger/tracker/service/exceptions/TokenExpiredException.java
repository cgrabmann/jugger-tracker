package at.jugger.tracker.service.exceptions;

import at.jugger.tracker.domain.LoginTokenEntity;
import lombok.Getter;

public class TokenExpiredException extends Exception {
    @Getter
    private final LoginTokenEntity loginTokenEntity;

    public TokenExpiredException(LoginTokenEntity loginTokenEntity) {
        super();
        this.loginTokenEntity = loginTokenEntity;
    }
}
