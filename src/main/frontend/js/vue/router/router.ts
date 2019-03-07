import Vue from 'vue';
import Router from 'vue-router';
import VueBreadcrumbs from 'vue2-breadcrumbs';
import Overview from '../component/Overview.vue'
import Loading from '../component/Loading.vue'
import User from '../component/User.vue'
import Users from '../component/Users.vue'
import Trainings from '../component/Trainings.vue'
import Training from '../component/Training.vue'
import Error from '../component/Error.vue'
import Login from '../component/Login.vue'
import store from '../store'

Vue.use(Router);
Vue.use(VueBreadcrumbs);

const router = new Router({
    routes: [
        {
            path: '/',
            component: Loading
        }, {
            path: '/overview',
            component: Overview,
            meta: {
                breadcrumb: 'Overview',
                requiresAuth: true
            }
        }, {
            path: '/user',
            component: Users,
            meta: {
                breadcrumb: 'Users',
                requiresAuth: true
            }
        }, {
            path: '/user/:id',
            component: User,
            meta: {
                breadcrumb: 'User',
                requiresAuth: true
            }
        }, {
            path: '/trainings',
            component: Trainings,
            meta: {
                breadcrumb: 'Trainings',
                requiresAuth: true
            }
        }, {
            path: '/trainings/:id',
            component: Training,
            meta: {
                breadcrumb: 'Training',
                requiresAuth: true
            }
        }, {
            path: '/login/:error?',
            component: Login,
            meta: {
                breadcrumb: 'Login'
            }
        }, {
            path: '/error',
            component: Error,
            meta: {
                breadcrumb: 'Error'
            }
        }, {
            path: '*',
            redirect: '/error'
        }
    ]
});

router.beforeEach((to, from, next) => {
    if (to.matched.some(route => route.meta.requiresAuth)) {
        if (store.state.UserModule.currentUser == null) {
            next('/');
            return;
        }
    }

    next();
});

export default router;