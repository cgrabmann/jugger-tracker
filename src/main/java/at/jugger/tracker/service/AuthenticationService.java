package at.jugger.tracker.service;

import at.jugger.tracker.dto.User;
import at.jugger.tracker.service.dto.LoginToken;
import at.jugger.tracker.service.exceptions.NoTokenException;
import at.jugger.tracker.service.exceptions.TokenAlreadyUsedException;
import at.jugger.tracker.service.exceptions.TokenExpiredException;

public interface AuthenticationService {
    LoginToken createLoginToken(User userEntity);

    void authenticate(String tokenId) throws NoTokenException, TokenAlreadyUsedException, TokenExpiredException;
}
