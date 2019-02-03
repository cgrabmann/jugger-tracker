package at.jugger.tracker.service.impl;

import at.jugger.tracker.domain.TrainingEntity;
import at.jugger.tracker.dto.Training;
import at.jugger.tracker.mapper.TrainingMapper;
import at.jugger.tracker.repository.TrainingRepository;
import at.jugger.tracker.service.TrainingService;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class TrainingServiceImpl implements TrainingService {

    private final TrainingRepository trainingRepository;
    private final TrainingMapper trainingMapper;

    public TrainingServiceImpl(TrainingRepository trainingRepository, TrainingMapper trainingMapper) {
        this.trainingRepository = trainingRepository;
        this.trainingMapper = trainingMapper;
    }

    @Override
    public @NotNull List<Training> getTrainings() {
        return trainingMapper.toDtos(trainingRepository.findAll());
    }

    @Override
    public Training getTraining(@NotNull LocalDate date) {
        TrainingEntity trainingEntity = trainingRepository.findByDate(date);
        return trainingMapper.toDto(trainingEntity);
    }

    @Override
    public void createTraining(@NotNull Training training) {
        TrainingEntity trainingEntity = trainingMapper.toEntity(training);
        trainingRepository.save(trainingEntity);
    }

    @Override
    public void updateTraining(@NotNull LocalDate date, @NotNull Training training) {
        TrainingEntity trainingEntity = Objects.requireNonNull(
                trainingRepository.findByDate(date),
                "Training with date '" + date + "' not fund."
        );

        trainingEntity = trainingMapper.toEntity(training, trainingEntity);
        trainingRepository.save(trainingEntity);
    }

    @Override
    public void deleteTraining(@NotNull LocalDate date) {
        TrainingEntity trainingEntity = trainingRepository.findByDate(date);
        trainingRepository.delete(trainingEntity);
    }
}
