import 'vuetify/dist/vuetify.min.css'
import 'material-design-icons-iconfont/dist/material-design-icons.css'

import Vue from 'vue';
import Vuetify from 'vuetify';
import store from './vue/store'
import Jugger from './vue/Jugger.vue'
import router from './vue/router/router';

Vue.use(Vuetify, {
    iconfont: 'md'
});


new Vue({
    el: '#app',
    store,
    router,
    data: {},
    render(r) {
        return r(Jugger, {});
    }
});