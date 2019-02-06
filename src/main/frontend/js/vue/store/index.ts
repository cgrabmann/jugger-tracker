import Vue from 'vue';
import Vuex, {StoreOptions} from 'vuex';
import {RootState} from './types';
import {UserModule} from './user';
import {TrainingModule} from "./trainings";

Vue.use(Vuex);

const store: StoreOptions<RootState> = {
    modules: {
        UserModule,
        TrainingModule
    }
};

export default new Vuex.Store<RootState>(store);