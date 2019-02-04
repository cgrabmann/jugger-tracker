<template>
    <v-app light>
        <v-bottom-nav v-if="$vuetify.breakpoint.smAndDown"
                      fixed
                      :value="true"
                      app>
            <v-btn depressed
                   to="/overview">
                <span>Überblick</span>
                <v-icon>home</v-icon>
            </v-btn>
            <v-btn depressed
                   to="/user">
                <span>User</span>
                <v-icon>account_box</v-icon>
            </v-btn>
        </v-bottom-nav>
        <v-navigation-drawer v-else
                             fixed
                             app
                             permanent>
            <v-list dense class="pt-0">
                <v-toolbar>
                    <v-toolbar-title>Jugger Vienna</v-toolbar-title>
                </v-toolbar>
                <v-spacer class="pt-3"></v-spacer>
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
            <keep-alive>
                <router-view></router-view>
            </keep-alive>
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