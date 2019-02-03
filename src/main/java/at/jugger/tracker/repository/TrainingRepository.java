package at.jugger.tracker.repository;

import at.jugger.tracker.domain.TrainingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface TrainingRepository extends JpaRepository<TrainingEntity, Long> {
    TrainingEntity findByDate(LocalDate date);
}
