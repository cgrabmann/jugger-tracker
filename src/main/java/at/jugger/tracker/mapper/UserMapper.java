package at.jugger.tracker.mapper;

import at.jugger.tracker.config.MapperConfig;
import at.jugger.tracker.domain.UserEntity;
import at.jugger.tracker.dto.User;
import at.jugger.tracker.dto.UserData;
import at.jugger.tracker.mapper.decorator.RoleDependendUserMapperDecorator;
import org.mapstruct.*;

import java.util.List;

@Mapper(config = MapperConfig.class)
@DecoratedWith(RoleDependendUserMapperDecorator.class)
public interface UserMapper {

    @Mappings({
            @Mapping(target = "id", source = "userId")
    })
    User toDto(UserEntity entity);

    List<User> toDtos(List<UserEntity> entities);

    @Mapping(target = "trackable", defaultValue = "false")
    @Mappings(
            @Mapping(target = "userId", ignore = true)
    )
    UserEntity toEntity(UserData user, @MappingTarget UserEntity entity);

    @Mapping(target = "trackable", defaultValue = "false")
    @Mappings(
            @Mapping(target = "userId", ignore = true)
    )
    UserEntity toEntity(UserData newUser);

    @InheritInverseConfiguration
    UserEntity toEntity(User user);

}
