package at.jugger.tracker.service.impl;

import at.jugger.tracker.dto.Training;
import at.jugger.tracker.service.TrainingService;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Service
public class TrainingServiceImpl implements TrainingService {

    public TrainingServiceImpl() {

    }

    @Override
    public List<Training> getTrainings() {
        return null;
    }

    @Override
    public Training getTraining(@NotNull LocalDate date) {
        return null;
    }

    @Override
    public void createTraining(@NotNull Training training) {

    }

    @Override
    public void updateTraining(@NotNull Training training) {

    }

    @Override
    public void deleteTraining(@NotNull LocalDate date) {

    }
}
