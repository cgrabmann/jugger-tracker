package at.jugger.tracker.controller;

import at.jugger.tracker.api.AuthenticationApiDelegate;
import at.jugger.tracker.dto.User;
import at.jugger.tracker.service.AuthenticationService;
import at.jugger.tracker.service.EmailService;
import at.jugger.tracker.service.UserService;
import at.jugger.tracker.service.dto.LoginToken;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
        return null;
    }

    @Override
    public ResponseEntity<Void> requestLoginToken(String email) {
        User user = userService.getUserByEmail(email);
        LoginToken loginToken = authenticationService.createLoginToken(user);

        ServletUriComponentsBuilder servletUriComponentsBuilder = ServletUriComponentsBuilder.fromCurrentRequest();
        servletUriComponentsBuilder.replacePath("/api/authentication/" + loginToken.getToken());

        emailService.sendAuthenticationEmail(loginToken, servletUriComponentsBuilder.build().toUriString());
        return ResponseEntity.ok().build();
    }
}
