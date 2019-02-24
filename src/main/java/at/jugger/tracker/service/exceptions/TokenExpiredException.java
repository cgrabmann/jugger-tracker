package at.jugger.tracker.service.exceptions;

import at.jugger.tracker.service.dto.LoginToken;
import lombok.Getter;

public class TokenExpiredException extends Exception {
    @Getter
    private final LoginToken loginToken;

    public TokenExpiredException(LoginToken loginToken) {
        super();
        this.loginToken = loginToken;
    }
}
