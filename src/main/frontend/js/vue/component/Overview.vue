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