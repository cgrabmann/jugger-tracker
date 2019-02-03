package at.jugger.tracker.controller;

import at.jugger.tracker.api.TrainingApiDelegate;
import at.jugger.tracker.dto.Training;
import at.jugger.tracker.service.TrainingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@RestController
public class TrainingController implements TrainingApiDelegate {
    private final TrainingService trainingService;

    public TrainingController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @Override
    public ResponseEntity<Void> createTraining(@NotNull Training training) {
        trainingService.createTraining(training);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> deleteTraining(@NotNull LocalDate date) {
        trainingService.deleteTraining(date);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Training> getTraining(@NotNull LocalDate date) {
        return ResponseEntity.ok(trainingService.getTraining(date));
    }

    @Override
    public ResponseEntity<List<Training>> getTrainings() {
        return ResponseEntity.ok(trainingService.getTrainings());
    }

    @Override
    public ResponseEntity<Void> updateTraining(@NotNull Training training) {
        trainingService.updateTraining(training);
        return ResponseEntity.ok().build();
    }
}
