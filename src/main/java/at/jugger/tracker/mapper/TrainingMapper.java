package at.jugger.tracker.mapper;

import at.jugger.tracker.config.MapperConfig;
import at.jugger.tracker.domain.TrainingEntity;
import at.jugger.tracker.dto.Training;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(config = MapperConfig.class, uses = UserMapper.class)
public interface TrainingMapper {

    Training toDto(TrainingEntity entity);

    List<Training> toDtos(List<TrainingEntity> entities);

    @InheritInverseConfiguration
    TrainingEntity toEntity(Training training, @MappingTarget TrainingEntity entity);

    @InheritConfiguration
    TrainingEntity toEntity(Training training);
}
