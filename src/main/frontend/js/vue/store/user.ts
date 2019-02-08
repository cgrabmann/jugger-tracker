import {Module} from 'vuex';
import {RootState, UserState} from './types';
import {User, UserData} from 'juggerApi'
import {UserAPI} from '../../api';

const namespaced = true;
const state: UserState = {
    editUser: {
        firstName: null,
        lastName: null,
        email: null,
        trackabel: true
    } as User,
    users: [] as Array<User>
};

export const UserModule: Module<UserState, RootState> = {
    namespaced,
    state,
    actions: {
        createUser(context, userData: UserData): any {
            return UserAPI.Instance.getUserAPI().createUser(userData)
                .then((response: User) => context.commit('userCreated', response));
        },
        updateUser(context, user: User): any {
            return UserAPI.Instance.getUserAPI().updateUser(user.id, user)
                .then(() => context.dispatch('getUsers'));
        },
        getUsers(context): any {
            return UserAPI.Instance.getUserAPI().getUsers()
                .then((response: User[]) => context.commit('usersLoaded', response));
        },
        getUser(context, id: number): any {
            return UserAPI.Instance.getUserAPI().getUser(id)
                .then((response: User) => context.commit('userLoaded', response));
        },
        deleteUser(context, id:number): any {
            return UserAPI.Instance.getUserAPI().deleteUser(id)
                .then(() => context.dispatch('getUsers'));
        }
    },
    mutations: {
        userCreated(state, user: User) {
            state.users.push(user);
        },
        userLoaded(state, user: User) {
            state.editUser = user;
        },
        usersLoaded(state, users: User[]) {
            state.users = users;
        }
    }
};
