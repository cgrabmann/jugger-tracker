package at.jugger.tracker.service;

import at.jugger.tracker.dto.User;
import at.jugger.tracker.dto.UserData;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface UserService {
    User getUser(@NotNull Long id);

    @NotNull List<User> getUsers();

    @NotNull User createUser(@NotNull UserData newUser);

    @NotNull User updateUser(Long id, @NotNull UserData user);

    void deleteUser(@NotNull Long id);
}
