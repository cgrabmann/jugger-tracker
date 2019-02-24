import {Training, User} from './api'

export interface RootState {

}

export interface UserState {
    currentUser: User,
    editUser: User,
    users: User[]
}

export interface TrainingState {
    trainings: Training[],
    editTraining: Training
}