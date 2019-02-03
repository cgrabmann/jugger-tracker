import {Module} from 'vuex';
import {RootState, UserState} from './types';
import {UserData, User} from './api'
import {UserAPI} from '../../api';

const namespaced = true;
const state: UserState = {
    user: {} as User
};

export const UserModule: Module<UserState, RootState> = {
    namespaced,
    state,
    actions: {
        createUser(context, userData: UserData): any {
            return UserAPI.Instance.getUserAPI().createUser(userData)
                .then((response: User) => context.commit('userLoaded', response));
        },
        updateUser(context, user: User): any {
            return UserAPI.Instance.getUserAPI().updateUser(user.id, user)
                .then((response: User) => context.commit('userLoaded', response));
        }
    },
    mutations: {
        userLoaded(state, user: User) {
            state.user = user;
        }
    }
};