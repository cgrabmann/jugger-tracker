package at.jugger.tracker.mapper;

import at.jugger.tracker.config.MapperConfig;
import at.jugger.tracker.domain.UserEntity;
import at.jugger.tracker.dto.User;
import at.jugger.tracker.dto.UserData;
import org.mapstruct.*;

import java.util.List;

@Mapper(config = MapperConfig.class)
public interface UserMapper {

    @Mappings({
            @Mapping(target = "id", source = "userId"),
            @Mapping(target = "email", source = "email", ignore = true),
            @Mapping(target = "role", source = "role", ignore = true)
    })
    User toDto(UserEntity entity);

    List<User> toDtos(List<UserEntity> entities);

    @Mappings({
            @Mapping(target = "userId", ignore = true),
            @Mapping(target = "email", source = "email", ignore = true),
            @Mapping(target = "role", source = "role", ignore = true),
            @Mapping(target = "trackable", defaultValue = "false")
    })
    UserEntity toEntity(UserData user, @MappingTarget UserEntity entity);

    @Mappings({
            @Mapping(target = "userId", ignore = true),
            @Mapping(target = "email", source = "email", ignore = true),
            @Mapping(target = "role", source = "role", ignore = true),
            @Mapping(target = "trackable", defaultValue = "false")
    })
    UserEntity toEntity(UserData newUser);

    @InheritInverseConfiguration
    UserEntity toEntity(User user);

}
