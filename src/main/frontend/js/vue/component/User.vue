<template>
    <div>
        <v-toolbar>
            <v-toolbar-title>User Anlegen</v-toolbar-title>
            <v-spacer></v-spacer>
            <v-toolbar-items>
                <v-btn depressed
                       color="primary"
                       :disabled="!valid">User speichern
                </v-btn>
            </v-toolbar-items>
        </v-toolbar>
        <v-form v-model="valid">
            <v-container fluid>
                <v-layout row wrap
                          justify-space-around>
                    <v-flex>
                        <v-text-field v-model="user.firstName"
                                      :counter="20"
                                      :rules="nameRules"
                                      label="Vorname"
                                      required></v-text-field>
                    </v-flex>
                    <v-flex>
                        <v-text-field v-model="user.lastName"
                                      :counter="20"
                                      :rules="nameRules"
                                      label="Nachname"
                                      required></v-text-field>
                    </v-flex>
                    <v-flex>
                        <v-text-field v-model="user.email"
                                      :counter="50"
                                      :rules="emailRules"
                                      label="Email"
                                      required></v-text-field>
                    </v-flex>
                </v-layout>
            </v-container>
        </v-form>
    </div>
</template>

<script lang="ts">
    import Vue from 'vue';
    import Component from 'vue-class-component';
    import {Action, State} from 'vuex-class';
    import {Namespace} from '../store/namespace';
    import {UserState} from '../store/types';
    import {User} from './api'

    @Component({
        beforeRouteEnter(to, from, next) {
            next(vm => {
                vm.openNew(to.params.id);
                next();
            })
        },
        beforeRouteUpdate(to, from, next) {
            this.openNew(to.params.id);
            next();
        }
    })
    export default class UserComponent extends Vue {
        @State(Namespace.USER.namespace) userState: UserState;
        @Action('getUser', Namespace.USER) getUser;
        valid = false;

        openNew(id: (string | number)) {
            this.userState.editUser = {
                firstName: null,
                lastName: null,
                email: null
            } as User;
            if (id !== "new") {
                this.getUser(id)
            }
        }

        get user(): User {
            return this.userState.editUser;
        }

        get nameRules(): any {
            return [
                this.requiredRule,
                this.lengthRule(20)
            ];
        }

        get emailRules(): any {
            return [
                this.requiredRule,
                this.lengthRule(50),
                value => /.+@.+\..+/.test(value) || 'Ungültige Email'
            ]
        }

        get requiredRule(): any {
            return value => !!value || 'Pflichtfeld';
        }

        lengthRule(length: number): any {
            return value => !!value && value.length <= length || 'Maximal ' + length + ' Zeichen erlaubt';
        }
    }
</script>

<style scoped>

</style>