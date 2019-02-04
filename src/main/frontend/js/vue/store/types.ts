import {Training, User} from './api'

export interface RootState {

}

export interface UserState {
    editUser: User,
    users: User[]
}

export interface TrainingState {
    trainings: Training[],
    editTraining: Training
}