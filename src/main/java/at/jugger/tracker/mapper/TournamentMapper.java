package at.jugger.tracker.mapper;

import at.jugger.tracker.config.MapperConfig;
import at.jugger.tracker.domain.TournamentEntity;
import at.jugger.tracker.dto.Tournament;
import at.jugger.tracker.dto.TournamentData;
import org.mapstruct.*;

import java.util.List;

@Mapper(config = MapperConfig.class, uses = ApplicationPhaseMapper.class)
public interface TournamentMapper {

    @Mappings({
            @Mapping(target = "id", source = "tournamentId")
    })
    Tournament toDto(TournamentEntity entity);

    List<Tournament> toDtos(List<TournamentEntity> entities);

    @InheritInverseConfiguration
    TournamentEntity toEntity(TournamentData tournament, @MappingTarget TournamentEntity entity);

    @InheritInverseConfiguration
    TournamentEntity toEntity(TournamentData tournament);
}
