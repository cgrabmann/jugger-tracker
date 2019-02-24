package at.jugger.tracker.service.exceptions;

import at.jugger.tracker.service.dto.LoginToken;
import lombok.Getter;

public class TokenAlreadyUsedException extends Exception {
    @Getter
    private final LoginToken loginToken;

    public TokenAlreadyUsedException(LoginToken loginToken) {
        super();
        this.loginToken = loginToken;
    }
}
