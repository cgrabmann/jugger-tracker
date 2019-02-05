import {Module} from 'vuex';
import {RootState, TrainingState} from './types';
import {Training, User} from './api'
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
        /*createTraining(context, training: Training): any {
            return TrainingAPI.Instance.getTrainingAPI().createTraining(training);
        },
        updateTraining(context, date: string, training: Training): any {
            return TrainingAPI.Instance.getTrainingAPI().updateTraining(date, training);
        },*/
        getTrainings(context): any {
            return TrainingAPI.Instance.getTrainingAPI().getTrainings().then((response: Training[]) => {
                context.commit('trainingsLoaded', response)
            });
        }
    },
    mutations: {
        trainingsLoaded(state, trainings: Training[]) {
            state.trainings = trainings;
        }
    }
};