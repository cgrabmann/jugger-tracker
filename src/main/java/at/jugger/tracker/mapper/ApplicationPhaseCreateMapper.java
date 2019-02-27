package at.jugger.tracker.mapper;

import at.jugger.tracker.config.MapperConfig;
import at.jugger.tracker.domain.ApplicationPhaseEntity;
import at.jugger.tracker.dto.ApplicationPhase;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(config = MapperConfig.class, uses = UserMapper.class)
public interface ApplicationPhaseCreateMapper {

    @Mappings({
            @Mapping(target = "id", source = "applicationPhaseId")
    })
    ApplicationPhase toDto(ApplicationPhaseEntity entity);

    List<ApplicationPhase> toDtos(List<ApplicationPhaseEntity> entities);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "applicationPhaseId", ignore = true)
    })
    ApplicationPhaseEntity toEntity(ApplicationPhase applicationPhase, @MappingTarget ApplicationPhaseEntity entity);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "applicationPhaseId", ignore = true)
    })
    ApplicationPhaseEntity toEntity(ApplicationPhase applicationPhase);
}
