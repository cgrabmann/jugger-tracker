package at.jugger.tracker.service.exceptions;

import at.jugger.tracker.domain.LoginTokenEntity;
import lombok.Getter;

public class TokenAlreadyUsedException extends Exception {
    @Getter
    private final LoginTokenEntity loginTokenEntity;

    public TokenAlreadyUsedException(LoginTokenEntity loginTokenEntity) {
        super();
        this.loginTokenEntity = loginTokenEntity;
    }
}
