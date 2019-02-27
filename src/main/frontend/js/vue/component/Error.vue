<template>
    <div>
        <v-toolbar fixed app>
            <v-toolbar-title>
                    Error 404
            </v-toolbar-title>
            <v-spacer></v-spacer>
            <v-btn round
                   color="primary"
                   @click="$router.go(-1)">
                Zurück
            </v-btn>
        </v-toolbar>
        <v-container fluid>
            <v-layout column>
                <v-flex>
                    <p>Unsere Kette ist leider gewickelt. Deshalb konnten wir deine gewünschte Seite nicht rechtzeitig abschlagen.</p>
                </v-flex>
            </v-layout>
        </v-container>
    </div>
</template>

<script lang="ts">
    import Vue from 'vue';
    import Component from 'vue-class-component';

    @Component
    export default class Error extends Vue {

    }
</script>

<style scoped>

</style>