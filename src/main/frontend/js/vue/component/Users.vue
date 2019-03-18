<template>
    <v-card>
        <v-toolbar fixed app>
            <v-toolbar-title>Mitglieder</v-toolbar-title>
            <v-spacer></v-spacer>
            <v-btn v-if="hasAdminRights"
                   round
                   dark
                   to="/user/new"
                   color="primary">
                <v-icon left>add</v-icon>
                anlegen
            </v-btn>
        </v-toolbar>
        <v-container fluid>
            <v-layout column
                      justify-space-around>
                <v-flex>
                    <v-data-table :headers="headers"
                                  :items="users"
                                  class="mb-3"
                                  hide-actions>
                        <template slot="items" slot-scope="props">
                            <tr @click="openUserAction(props.item)">
                                <td class="text-xs-center">{{props.item.firstName}}</td>
                                <td class="text-xs-center">{{props.item.lastName}}</td>
                                <td v-if="hasAdminRights"><v-icon>keyboard_arrow_right</v-icon></td>
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
    import {Action, Getter, State} from 'vuex-class';
    import {Training, User} from 'juggerApi';

    @Component
    export default class Users extends Vue {
        @State(Namespace.USER.namespace) userState: UserState;
        @Action('getUsers', Namespace.USER) getUsers: any;
        @Action('deleteUser', Namespace.USER) deleteUser: any;
        @Getter('hasAdminRights', Namespace.USER) hasAdminRights: boolean;

        get headers() {
            let ret = [
                {
                    text: 'Vorname',
                    value: 'firstName',
                    align: 'center',
                    width: '',
                    sortable: false
                },
                {
                    text: 'Nachname',
                    value: 'lastName',
                    align: 'center',
                    width: '',
                    sortable: false
                }
            ];

            if (this.hasAdminRights) {
                ret.push({
                    text: '',
                    value: '',
                    align: '',
                    width: '1px',
                    sortable: false
                })
            }

            return ret;
        }

        openUserAction(user: User) {
            if (this.hasAdminRights) {
                this.$router.push('/user/' + user.id);
            }
        }

        beforeMount() {
            this.getUsers();
        }

        get users() {
            return this.userState.users;
        }
    }
</script>

<style lang="less"
       scoped>

    @media only screen and (max-width: 500px) {
        .container {
            padding: 0;
        }
    }

</style>