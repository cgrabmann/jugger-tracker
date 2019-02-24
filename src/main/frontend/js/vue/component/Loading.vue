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
    import {Action, State} from 'vuex-class';
    import {UserState} from '../store/types';

    @Component({
        beforeRouteEnter(to, from, next) {
            next(vm => {
                if (vm.userState.currentUser != null) {
                    next('/overview');
                } else {
                    next();
                }
            });
        }
    })
    export default class Loading extends Vue {
        @State(Namespace.USER.namespace) userState: UserState;
        @Action('getCurrentUser', Namespace.USER) getCurrentUser: any;

        beforeMount() {
            if (this.userState.currentUser == null) {
                this.getCurrentUser()
                    .then(() => this.$router.push('/overview'))
                    .catch(() => this.$router.push('/login'));
            }
        }
    }
</script>

<style scoped>

</style>