<template>
    <v-app light>
        <v-bottom-nav v-if="$vuetify.breakpoint.smAndDown"
                      fixed
                      :value="true"
                      app>
            <v-btn flat
                   to="/overview">
                <span>Überblick</span>
                <v-icon>home</v-icon>
            </v-btn>
            <v-btn flat
                   to="/user">
                <span>User</span>
                <v-icon>account_box</v-icon>
            </v-btn>
        </v-bottom-nav>
        <v-navigation-drawer v-else
                             fixed
                             app
                             permanent>
            <v-list light dense class="pt-2">
                <v-list-tile to="/overview">
                    <v-list-tile-action>
                        <v-icon>home</v-icon>
                    </v-list-tile-action>
                    <v-list-tile-content>
                        <v-list-tile-title>Überblick</v-list-tile-title>
                    </v-list-tile-content>
                </v-list-tile>
                <v-list-tile to="/user">
                    <v-list-tile-action>
                        <v-icon>account_box</v-icon>
                    </v-list-tile-action>
                    <v-list-tile-content>
                        <v-list-tile-title>User</v-list-tile-title>
                    </v-list-tile-content>
                </v-list-tile>
            </v-list>
        </v-navigation-drawer>
        <v-content>
            <v-container fluid>
                <router-view/>
            </v-container>
        </v-content>
    </v-app>
</template>
<script lang="ts">
    import Vue from 'vue';
    import Component from 'vue-class-component';

    @Component
    export default class Jugger extends Vue {

    }
</script>