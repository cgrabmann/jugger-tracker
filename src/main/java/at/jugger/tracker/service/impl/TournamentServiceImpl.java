package at.jugger.tracker.service.impl;

import at.jugger.tracker.domain.TournamentEntity;
import at.jugger.tracker.dto.Tournament;
import at.jugger.tracker.dto.TournamentData;
import at.jugger.tracker.mapper.TournamentMapper;
import at.jugger.tracker.repository.TournamentRepository;
import at.jugger.tracker.service.TournamentService;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Service
public class TournamentServiceImpl implements TournamentService {

    private final TournamentRepository tournamentRepository;
    private final TournamentMapper tournamentMapper;

    TournamentServiceImpl(TournamentRepository tournamentRepository, TournamentMapper tournamentMapper) {
        this.tournamentRepository = tournamentRepository;
        this.tournamentMapper = tournamentMapper;
    }

    @Override
    public Tournament getTournament(@NotNull Long id) {
        return tournamentMapper.toDto(tournamentRepository.findByTournamentId(id));
    }

    @Override
    public @NotNull List<Tournament> getTournaments() {
        return tournamentMapper.toDtos(tournamentRepository.findAll());
    }

    @Override
    public @NotNull Tournament createTournament(@NotNull TournamentData newTournament) {
        TournamentEntity tournamentEntity = tournamentMapper.toEntity(newTournament);

        tournamentEntity = tournamentRepository.save(tournamentEntity);

        return tournamentMapper.toDto(tournamentEntity);
    }

    @Override
    public @NotNull Tournament updateTournament(Long id, TournamentData tournament) {
        TournamentEntity tournamentEntity = Objects.requireNonNull(
                tournamentRepository.findByTournamentId(id),
                "Tournament with ID '" + id + "' not fund."
        );

        tournamentEntity = tournamentMapper.toEntity(tournament, tournamentEntity);
        tournamentEntity = tournamentRepository.save(tournamentEntity);

        return tournamentMapper.toDto(tournamentEntity);
    }

    @Override
    public void deleteTournament(@NotNull Long id) {
        tournamentRepository.deleteById(id);
    }


}
