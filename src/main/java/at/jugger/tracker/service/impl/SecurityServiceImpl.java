package at.jugger.tracker.service.impl;

import at.jugger.tracker.dto.User;
import at.jugger.tracker.service.SecurityService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {
    @Override
    public boolean currentUserHasRole(String role) {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities()
                                    .stream().anyMatch(a -> a.getAuthority().equals("ROLE_" + role));
    }

    @Override
    public boolean isCurrentUser(User user) {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals(user.getEmail());
    }
}
