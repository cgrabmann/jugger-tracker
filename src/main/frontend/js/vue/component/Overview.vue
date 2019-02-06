<template>
    <div>
        <v-toolbar>
            <v-toolbar-title v-if="$vuetify.breakpoint.smAndDown">Jugger Vienna</v-toolbar-title>
            <v-spacer></v-spacer>
            <v-toolbar-items>
                <v-btn depressed
                       color="primary"
                       to="/trainings/new">
                    neues Training anlegen
                </v-btn>
            </v-toolbar-items>
        </v-toolbar>
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