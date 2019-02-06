<template>
    <div>
        <v-btn flat
               to="/trainings/new">
            <v-icon>colorize</v-icon>
            <span>Neues Training anlegen</span>
        </v-btn>
        <v-btn flat
               to="/trainings">
            <v-icon>format_list_bulleted</v-icon>
            <span>Trainings-Übersicht</span>
        </v-btn>
        <v-btn flat
               to="/users">
            <v-icon>account_circle</v-icon>
            <span>User-Verwaltung</span>
        </v-btn>
    </div>
</template>

<script lang="ts">
    import Vue from 'vue';
    import Component from 'vue-class-component';

    @Component
    export default class Overview extends Vue {

    }
</script>

<style scoped>

</style>