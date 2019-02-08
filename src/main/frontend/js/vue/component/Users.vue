<template>
    <v-card>
        <v-toolbar fixed app>
            <v-toolbar-title>Mitglieder</v-toolbar-title>
            <v-btn fab
                   dark
                   absolute
                   right
                   bottom
                   to="/user/new"
                   color="primary">
                <v-icon>add</v-icon>
            </v-btn>
        </v-toolbar>
        <v-container fluid>
            <v-layout column
                      justify-space-around>
                <v-flex>
                    <v-data-table :headers="headers"
                                  :items="users"
                                  class="mb-3">
                        <template slot="items" slot-scope="props">
                            <tr @click="openUserAction(props.item)">
                                <td class="text-xs-center">{{props.item.firstName}}</td>
                                <td class="text-xs-center">{{props.item.lastName}}</td>
                                <td><v-icon>open_in_new</v-icon></td>
                            </tr>
                        </template>
                    </v-data-table>
                </v-flex>
            </v-layout>
        </v-container>
    </v-card>
</template>

<script lang="ts">
    import Vue from 'vue';
    import Component from 'vue-class-component';
    import {UserState} from '../store/types';
    import {Namespace} from '../store/namespace';
    import {Action, State} from 'vuex-class';
    import {Training, User} from 'juggerApi';

    @Component
    export default class Users extends Vue {
        @State(Namespace.USER.namespace) userState: UserState;
        @Action('getUsers', Namespace.USER) getUsers: any;
        @Action('deleteUser', Namespace.USER) deleteUser: any;

        get headers() {
            return [
                {
                    text: 'Vorname',
                    value: 'firstName',
                    align: 'center',
                    sortable: false
                },
                {
                    text: 'Nachname',
                    value: 'lastName',
                    align: 'center',
                    sortable: false
                },
                {
                    text: '',
                    sortable: false,
                    width: '1px'
                }
            ];
        }

        openUserAction(user: User) {
            this.$router.push('/user/' + user.id);
        }

        beforeMount() {
            this.getUsers();
        }

        get users() {
            return this.userState.users;
        }
    }
</script>

<style scoped>

</style>