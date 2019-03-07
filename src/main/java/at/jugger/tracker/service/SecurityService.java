package at.jugger.tracker.service;

import at.jugger.tracker.dto.User;

public interface SecurityService {
    boolean currentUserHasRole(String role);

    boolean isCurrentUser(User user);
}
