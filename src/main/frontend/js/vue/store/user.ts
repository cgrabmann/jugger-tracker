import {Module} from 'vuex';
import {RootState, UserState} from './types';
import {User, UserData} from './api'
import {UserAPI} from '../../api';

const namespaced = true;
const state: UserState = {
    user: {} as User,
    users: [] as Array<User>
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
        },
        getUsers(context): any {
            return UserAPI.Instance.getUserAPI().getUsers()
                .then((response: User[]) => context.commit('usersLoaded', response));
        }
    },
    mutations: {
        userLoaded(state, user: User) {
            state.user = user;
        },
        usersLoaded(state, users: User[]) {
            state.users = users;
        }
    }
};
