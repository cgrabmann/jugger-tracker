package at.jugger.tracker.controller;

import at.jugger.tracker.api.UserApiDelegate;
import at.jugger.tracker.dto.NewUser;
import at.jugger.tracker.dto.User;
import at.jugger.tracker.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
public class UserController implements UserApiDelegate {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<User> getUser(@NotNull Long id) {
        User user = userService.getUser(id);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(userService.getUser(id));
    }

    @Override
    public ResponseEntity<User> createUser(@NotNull NewUser newUser) {
        return ResponseEntity.ok(userService.createUser(newUser));
    }

    @Override
    public ResponseEntity<Void> deleteUser(@NotNull Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @Override
    public ResponseEntity<User> updateUser(@NotNull Long id, @NotNull User user) {
        if (!id.equals(user.getId())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Path user id (" + id + ") and id of user object (" + user.getId() + ") do not match"
            );
        }

        return ResponseEntity.ok(userService.updateUser(user));
    }
}
