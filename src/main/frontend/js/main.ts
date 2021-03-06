import 'vuetify/dist/vuetify.min.css'
import 'material-design-icons-iconfont/dist/material-design-icons.css'

import Vue from 'vue';
import Vuetify from 'vuetify';
import de from './vue/locale/de'
import colors from 'vuetify/es5/util/colors'
import store from './vue/store'
import Jugger from './vue/Jugger.vue'
import router from './vue/router/router';
import {TrainingAPI, UserAPI, AuthenticationAPI} from './api';

Vue.use(Vuetify, {
    iconfont: 'md',
    theme: {
        primary: colors.red.darken3,
        error: colors.red.darken4,
        success: colors.indigo
    },
    lang: {
        locales: {de},
        current: 'de'
    }
});

new Vue({
    el: '#app',
    store,
    router,
    data: {},
    beforeMount() {
        let host: string = undefined;
        if (process.env.NODE_ENV === 'development') {
            host = 'http://localhost:8080'
        }

        UserAPI.Instance.init(host);
        TrainingAPI.Instance.init(host);
        AuthenticationAPI.Instance.init(host);
    },
    render(r) {
        return r(Jugger, {});
    }
});