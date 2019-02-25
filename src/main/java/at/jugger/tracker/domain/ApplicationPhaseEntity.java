package at.jugger.tracker.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "application_phase")
@NoArgsConstructor
public class ApplicationPhaseEntity {
    @Id
    @Column(name = "application_phase_id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private Long applicationPhaseId;

    @Column(name = "from", nullable = false)
    @NonNull
    private LocalDate from;

    @Column(name = "to", nullable = false)
    @NonNull
    private LocalDate to;

    @ManyToMany
    @JoinTable(name = "user_applies_for_phase",
            joinColumns = @JoinColumn(name = "application_phase_id", referencedColumnName = "application_phase_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"))
    private List<UserEntity> participants;
}
