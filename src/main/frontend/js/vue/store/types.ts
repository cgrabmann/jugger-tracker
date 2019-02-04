import {Training, User} from './api'

export interface RootState {

}

export interface UserState {
    user: User,
    users: User []
}

export interface TrainingState {
    trainings: Training[]
}