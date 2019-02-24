package at.jugger.tracker.service.exceptions;

public class UnableToSendAuthenticationEmailException extends Exception {
    public UnableToSendAuthenticationEmailException(Exception exception) {
        super(exception);
    }
}
