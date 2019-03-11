package at.jugger.tracker.service.impl;

import at.jugger.tracker.domain.TrainingEntity;
import at.jugger.tracker.dto.Training;
import at.jugger.tracker.dto.User;
import at.jugger.tracker.exceptions.TrainingNotFoundException;
import at.jugger.tracker.exceptions.UserNotTrackableException;
import at.jugger.tracker.mapper.TrainingMapper;
import at.jugger.tracker.repository.TrainingRepository;
import at.jugger.tracker.service.TrainingService;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

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
    public @NotNull Training createTraining(@NotNull Training training) {
        checkTrackableFieldAndThrow(training);
        TrainingEntity trainingEntity = trainingMapper.toEntity(training);
        return trainingMapper.toDto(trainingRepository.save(trainingEntity));
    }

    @Override
    public @NotNull Training updateTraining(@NotNull LocalDate date, @NotNull Training training) {
        checkTrackableFieldAndThrow(training);
        TrainingEntity trainingEntity = trainingRepository.findByDate(date);

        if (trainingEntity == null) {
            throw new TrainingNotFoundException("date", date.toString());
        }

        trainingEntity = trainingMapper.toEntity(training, trainingEntity);
        return trainingMapper.toDto(trainingRepository.save(trainingEntity));
    }

    @Override
    public void deleteTraining(@NotNull LocalDate date) {
        TrainingEntity trainingEntity = trainingRepository.findByDate(date);
        trainingRepository.delete(trainingEntity);
    }

    private void checkTrackableFieldAndThrow(Training training) {
        for (User participant : training.getParticipants()) {
            if (!participant.getTrackable()) {
                throw new UserNotTrackableException(participant);
            }
        }
    }
}
