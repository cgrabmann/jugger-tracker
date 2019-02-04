import 'vuetify/dist/vuetify.min.css'
import 'material-design-icons-iconfont/dist/material-design-icons.css'

import Vue from 'vue';
import Vuetify from 'vuetify';
import colors from 'vuetify/es5/util/colors'
import store from './vue/store'
import Jugger from './vue/Jugger.vue'
import router from './vue/router/router';
import {UserAPI} from './api';

Vue.use(Vuetify, {
    iconfont: 'md',
    theme: {
        primary: colors.red.darken3
    }
});


new Vue({
    el: '#app',
    store,
    router,
    data: {},
    beforeMount() {
        let host: string = null;
        if (process.env.NODE_ENV === 'development') {
            host = 'localhost:8080'
        }

        UserAPI.Instance.init(host);
    },
    render(r) {
        return r(Jugger, {});
    }
});