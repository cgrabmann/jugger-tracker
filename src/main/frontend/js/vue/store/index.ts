import Vue from 'vue';
import Vuex, {StoreOptions} from 'vuex';
import {RootState} from './types';
import {UserModule} from './user';

Vue.use(Vuex);

const store: StoreOptions<RootState> = {
    modules: {
        UserModule
    }
};

export default new Vuex.Store<RootState>(store);