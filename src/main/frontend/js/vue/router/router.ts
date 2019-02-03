import Vue from 'vue';
import Router from 'vue-router';
import VueBreadcrumbs from 'vue2-breadcrumbs';
import Overview from '../component/Overview.vue'
import User from '../component/User.vue'
import Users from '../component/Users.vue'

Vue.use(Router);
Vue.use(VueBreadcrumbs);

export default new Router({
    routes: [
        {
            path: '/',
            redirect: '/overview'
        }, {
            path: '/overview',
            component: Overview,
            meta: {
                breadcrumb: 'Overview'
            }
        }, {
            path: '/user',
            component: Users,
            meta: {
                breadcrumb: 'Users'
            }
        }, {
            path: '/user/:id',
            component: User,
            meta: {
                breadcrumb: 'User'
            }
        }
    ]
});