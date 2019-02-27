<template>
    <div>
        <v-toolbar fixed app>
            <v-toolbar-title>
                <template v-if="$vuetify.breakpoint.smAndDown">
                    Jugger Vienna
                </template>
                <template v-else>
                    Überblick
                </template>
            </v-toolbar-title>
            <v-spacer></v-spacer>
            <v-btn round
                   color="primary"
                   @click="logout()">
                abmelden
            </v-btn>
        </v-toolbar>
    </div>
</template>

<script lang="ts">
    import Vue from 'vue';
    import Component from 'vue-class-component';
    import {AuthenticationAPI} from '../../api';

    @Component
    export default class Overview extends Vue {

        logout() {
            AuthenticationAPI.Instance.getAuthenticationAPI().logout()
                .then(() => {
                    this.$root.$store.state.UserModule.currentUser = null;
                    this.$router.push('/login');
                });
        }

    }
</script>

<style scoped>

</style>