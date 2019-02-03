package at.jugger.tracker.service;

import javax.validation.constraints.NotNull;

public interface EmailService {
    void send(@NotNull String to, @NotNull String subject, @NotNull String text);
}
