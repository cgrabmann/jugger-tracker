package at.jugger.tracker.controller;

import at.jugger.tracker.api.AuthenticationApiDelegate;
import at.jugger.tracker.dto.User;
import at.jugger.tracker.service.AuthenticationService;
import at.jugger.tracker.service.EmailService;
import at.jugger.tracker.service.UserService;
import at.jugger.tracker.service.dto.LoginToken;
import at.jugger.tracker.service.exceptions.NoTokenException;
import at.jugger.tracker.service.exceptions.TokenAlreadyUsedException;
import at.jugger.tracker.service.exceptions.TokenExpiredException;
import at.jugger.tracker.service.exceptions.UnableToSendAuthenticationEmailException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Slf4j
@RestController
public class AuthenticationController implements AuthenticationApiDelegate {
    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final EmailService emailService;

    public AuthenticationController(AuthenticationService authenticationService, UserService userService, EmailService emailService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
        this.emailService = emailService;
    }

    @Override
    public ResponseEntity<Void> authenticate(String tokenId) {
        try {
            authenticationService.authenticate(tokenId);
            return ResponseEntity.ok().build();
        } catch (NoTokenException e) {
            log.error("Token not found", e);
            return ResponseEntity.badRequest().build();
        } catch (TokenAlreadyUsedException e) {
            log.error("Given token has already been used", e);
            return ResponseEntity.badRequest().build();
        } catch (TokenExpiredException e) {
            log.error("Given token is expired", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<Void> requestLoginToken(String email) {
        User user = userService.getUserByEmail(email);
        LoginToken loginToken = authenticationService.createLoginToken(user);

        ServletUriComponentsBuilder servletUriComponentsBuilder = ServletUriComponentsBuilder.fromCurrentRequest();
        servletUriComponentsBuilder.replacePath("/api/authentication/" + loginToken.getToken());

        try {
            emailService.sendAuthenticationEmail(loginToken, servletUriComponentsBuilder.build().toUriString());
        } catch (UnableToSendAuthenticationEmailException e) {
            log.error("Unable to send authentication email", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok().build();
    }
}
