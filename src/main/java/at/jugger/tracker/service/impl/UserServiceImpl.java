package at.jugger.tracker.service.impl;

import at.jugger.tracker.domain.UserEntity;
import at.jugger.tracker.dto.User;
import at.jugger.tracker.dto.UserData;
import at.jugger.tracker.mapper.UserMapper;
import at.jugger.tracker.repository.UserRepository;
import at.jugger.tracker.service.UserService;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public User getUser(@NotNull Long id) {
        return userMapper.toDto(userRepository.findByUserId(id));
    }

    @Override
    public @NotNull List<User> getUsers() {
        return userMapper.toDtos(userRepository.findAll());
    }

    @Override
    public @NotNull User createUser(@NotNull UserData newUser) {
        UserEntity userEntity = userMapper.toEntity(newUser);

        userEntity = userRepository.save(userEntity);

        return userMapper.toDto(userEntity);
    }

    @Override
    public @NotNull User updateUser(Long id, UserData user) {
        UserEntity userEntity = Objects.requireNonNull(
                userRepository.findByUserId(id),
                "User with ID '" + id + "' not fund."
        );

        userEntity = userMapper.toEntity(user, userEntity);
        userEntity = userRepository.save(userEntity);

        return userMapper.toDto(userEntity);
    }

    @Override
    public void deleteUser(@NotNull Long id) {
        userRepository.deleteById(id);
    }


}
