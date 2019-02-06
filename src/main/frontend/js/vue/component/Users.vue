<template>
    <div>
        <v-toolbar>
            <v-toolbar-title>Mitglieder</v-toolbar-title>
            <v-spacer></v-spacer>
            <v-toolbar-items>
                <v-btn depressed
                       to="/user/new"
                       color="primary">Mitgleid anlegen
                </v-btn>
            </v-toolbar-items>
        </v-toolbar>
    </div>
</template>

<script lang="ts">
    import Vue from 'vue';
    import Component from 'vue-class-component';
    import {Store} from 'vuex';
    import {Namespace} from '../store/namespace';
    import {UserState} from '../store/types';
    import {State} from 'vuex-class';

    @Component
    export default class Users extends Vue {

    }
</script>

<style scoped>

</style>