import 'vuetify/dist/vuetify.min.css'
import 'material-design-icons-iconfont/dist/material-design-icons.css'

import Vue from 'vue';
import Vuetify from 'vuetify';
import Test from './vue/Test.vue'

Vue.use(Vuetify, {iconfont: 'md'});


new Vue({
    el: '#app',
    render(r) {
        return r(Test, {});
    }
});