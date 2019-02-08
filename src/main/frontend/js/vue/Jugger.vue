<template>
    <v-app light>
        <v-bottom-nav v-if="$vuetify.breakpoint.smAndDown"
                      fixed
                      :value="true"
                      app>
            <template v-for="route in routes">
                <v-btn depressed
                       :to="route.route"
                       color="primary--text">
                    <span>{{ route.title }}</span>
                    <v-icon>{{ route.icon }}</v-icon>
                </v-btn>
            </template>
        </v-bottom-nav>
        <v-navigation-drawer v-else
                             fixed
                             app
                             permanent>
            <v-list dense
                    class="pt-0">
                <v-toolbar>
                    <v-toolbar-title>Jugger Vienna</v-toolbar-title>
                </v-toolbar>
                <v-spacer class="pt-3"></v-spacer>
                <template v-for="route in routes">
                    <v-list-tile :to="route.route">
                        <v-list-tile-action>
                            <v-icon>{{ route.icon }}</v-icon>
                        </v-list-tile-action>
                        <v-list-tile-content>
                            <v-list-tile-title>{{ route.title }}</v-list-tile-title>
                        </v-list-tile-content>
                    </v-list-tile>
                </template>
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

        get routes(): any {
            return [
                {
                    title: 'Überblick',
                    icon: 'home',
                    route: '/overview'
                },
                {
                    title: 'Training',
                    icon: 'format_list_bulleted',
                    route: '/trainings'
                },
                {
                    title: 'Mitglieder',
                    icon: 'account_box',
                    route: '/user'
                }
            ]
        }

    }
</script>

<style lang="less">
    .v-speed-dial--bottom.v-speed-dial--absolute {
        bottom: 0;
    }

    .v-btn--floating.v-btn--router {
        .v-icon {
            display: inherit;
        }
    }

    .speed-dial-button {
        .v-btn__content {
            height: 25px;
        }
    }
</style>