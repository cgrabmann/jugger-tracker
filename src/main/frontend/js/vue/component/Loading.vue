<template>
    <v-container fill-height>
        <v-toolbar fixed
                   app>
        </v-toolbar>
        <v-layout align-center
                  justify-center
                  row
                  fill-height>
            <v-progress-circular
                    :size="100"
                    :width="10"
                    color="primary"
                    indeterminate
            ></v-progress-circular>
        </v-layout>
    </v-container>
</template>

<script lang="ts">
    import Vue from 'vue';
    import Component from 'vue-class-component';
    import {Namespace} from '../store/namespace';
    import {Action, Getter, State} from 'vuex-class';
    import {UserState} from '../store/types';

    @Component({
        beforeRouteEnter(to, from, next) {
            next(vm => {
                if (vm.isLoggedIn) {
                    next('/overview');
                } else {
                    vm.getCurrentUser()
                        .then(() => vm.$router.push('/overview'))
                        .catch(() => vm.$router.push('/login'));
                    next();
                }
            });
        }
    })
    export default class Loading extends Vue {
        @State(Namespace.USER.namespace) userState: UserState;
        @Action('getCurrentUser', Namespace.USER) getCurrentUser: any;
        @Getter('isLoggedIn', Namespace.USER) isLoggedIn: boolean;
    }
</script>

<style scoped>

</style>