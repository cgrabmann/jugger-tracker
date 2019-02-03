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
            @Mapping(target = "firstName", source = "firstName"),
            @Mapping(target = "lastName", source = "lastName"),
            @Mapping(target = "email", source = "email")
    })
    User toDto(UserEntity entity);

    List<User> toDtos(List<UserEntity> entities);

    @InheritInverseConfiguration
    UserEntity toEntity(UserData user, @MappingTarget UserEntity entity);

    @InheritConfiguration
    UserEntity toEntity(UserData newUser);

}
