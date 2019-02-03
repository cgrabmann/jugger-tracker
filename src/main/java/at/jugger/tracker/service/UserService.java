package at.jugger.tracker.service;

import at.jugger.tracker.dto.NewUser;
import at.jugger.tracker.dto.User;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface UserService {
    User getUser(@NotNull Long id);

    List<User> getUsers();

    @NotNull User createUser(@NotNull NewUser newUser);

    @NotNull User updateUser(@NotNull User user);

    void deleteUser(@NotNull Long id);
}
