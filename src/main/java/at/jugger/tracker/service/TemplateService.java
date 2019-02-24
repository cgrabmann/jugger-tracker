package at.jugger.tracker.service;

import at.jugger.tracker.service.dto.LoginToken;
import at.jugger.tracker.service.exceptions.UnableToSendAuthenticationEmailException;

public interface TemplateService {
    String getAuthenticationEmailService(LoginToken loginToken, String authenticationUrl) throws UnableToSendAuthenticationEmailException;
}
