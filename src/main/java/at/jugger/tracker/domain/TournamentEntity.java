package at.jugger.tracker.domain;

import at.jugger.tracker.dto.Tournament;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "tournament")
@NoArgsConstructor
public class TournamentEntity {
    @Id
    @Column(name = "tournament_id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private Long tournamentId;

    @Column(name = "name", nullable = false)
    @NonNull
    private String name;

    @Column(name = "date", nullable = false)
    @NonNull
    private LocalDate date;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private Tournament.StateEnum state;

    @Column(name = "max_teams")
    private int maxTeams;

    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JoinColumn(name = "tournament_id")
    private List<ApplicationPhaseEntity> applicationPhases;
}
