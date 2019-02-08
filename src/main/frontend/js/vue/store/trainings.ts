import {Module} from 'vuex';
import {RootState, TrainingState} from './types';
import {Training, User} from 'juggerApi'
import {TrainingAPI} from '../../api';

const namespaced = true;
const state: TrainingState = {
    trainings: [] as Training[],
    editTraining: {
        date: null,
        type: "klein",
        participants: [] as User[]
    } as Training
};

export const TrainingModule: Module<TrainingState, RootState> = {
    namespaced,
    state,
    actions: {
        createTraining(context, training: Training): any {
            return TrainingAPI.Instance.getTrainingAPI().createTraining(training)
                .then((response: Training) => context.commit('trainingCreated', response));
        },
        updateTraining(context, data: { date: string, training: Training }): any {
            return TrainingAPI.Instance.getTrainingAPI().updateTraining(data.date, data.training)
                .then(() => context.dispatch('getTrainings'));
        },
        deleteTraining(context, date: string): any {
            return TrainingAPI.Instance.getTrainingAPI().deleteTraining(date)
                .then(() => context.dispatch('getTrainings'));
        },
        getTraining(context, date: string): any {
            return TrainingAPI.Instance.getTrainingAPI().getTraining(date)
                .then((response: Training) => context.commit('trainingLoaded', response));
        },
        getTrainings(context): any {
            return TrainingAPI.Instance.getTrainingAPI().getTrainings()
                .then((response: Training[]) => context.commit('trainingsLoaded', response));
        }
    },
    mutations: {
        trainingCreated(state, user: User) {
            state.trainings.push(user);
        },
        trainingsLoaded(state, trainings: Training[]) {
            state.trainings = trainings;
        },
        trainingLoaded(state, training: Training) {
            state.editTraining = training;
        }
    }
};