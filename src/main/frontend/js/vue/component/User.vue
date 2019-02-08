<template>
    <v-card id="user">
        <v-toolbar fixed
                   app>
            <v-toolbar-title>{{ toolbarTitle }}</v-toolbar-title>
            <v-btn v-if="id === 'new'"
                   fab
                   dark
                   absolute
                   right
                   bottom
                   color="indigo"
                   @click="saveUser()">
                <v-icon>save</v-icon>
            </v-btn>
            <v-speed-dial v-else
                          v-model="fab"
                          absolute
                          bottom
                          right
                          direction="bottom"
                          transition="slide-y-transition">
                <v-btn v-model="fab"
                       :loading="saving"
                       slot="activator"
                       color="primary"
                       dark
                       fab
                       class="speed-dial-button">
                    <v-icon>menu</v-icon>
                    <v-icon>close</v-icon>
                </v-btn>
                <v-btn fab
                       dark
                       small
                       color="indigo"
                       @click="saveUser()">
                    <v-icon>save</v-icon>
                </v-btn>
                <v-btn v-if="!!user.id"
                       fab
                       dark
                       small
                       color="secondary"
                       @click="removeUser()">
                    <v-icon>delete</v-icon>
                </v-btn>
            </v-speed-dial>
        </v-toolbar>
        <v-form v-model="valid">
            <v-container fluid>
                <v-layout column
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
                    <v-flex>
                        <v-switch
                                v-model="user.trackable"
                                label="Trainingsbeteiligung tracken"
                                color="primary"></v-switch>
                    </v-flex>
                </v-layout>
            </v-container>
        </v-form>
    </v-card>
</template>

<script lang="ts">
    import Vue from 'vue';
    import Component from 'vue-class-component';
    import {Action, State} from 'vuex-class';
    import {Namespace} from '../store/namespace';
    import {UserState} from '../store/types';
    import {User} from 'juggerApi'

    @Component({
        beforeRouteEnter(to, from, next) {
            const id = to.params.id;
            if (isNaN(Number(id)) && id !== 'new') {
                next({path: '/error'});
            } else {
                next(vm => {
                    vm.openNew(to.params.id);
                    next();
                });
            }
        },
        beforeRouteUpdate(to, from, next) {
            const id = to.params.id;
            if (isNaN(Number(id)) && id !== 'new') {
                next({path: '/error'});
            } else {
                this.openNew(to.params.id);
                next();
            }
        }
    })
    export default class UserComponent extends Vue {
        @State(Namespace.USER.namespace) userState: UserState;
        @Action('getUser', Namespace.USER) getUser;
        @Action('createUser', Namespace.USER) createUser;
        @Action('updateUser', Namespace.USER) updateUser;
        @Action('deleteUser', Namespace.USER) deleteUser;
        valid: boolean = false;
        id: (string | number) = null;
        saving: boolean = false;
        fab: boolean = false;

        openNew(id: (string | number)) {
            this.userState.editUser = {
                firstName: null,
                lastName: null,
                email: null,
                trackable: true
            } as User;
            this.id = id;
            if (id !== "new") {
                this.getUser(id);
            }
        }

        saveUser() {
            this.fab = false;
            this.saving = true;
            let promise: Promise;
            if (this.id === 'new') {
                promise = this.createUser(this.user);
            } else {
                promise = this.updateUser(this.user);
            }
            promise.then(() => {
                this.$router.push('/user')
            }).finally(() => {
                this.saving = false;
            })
        }

        removeUser() {
            this.fab = false;
            if (confirm('Mitglied "' + this.user.firstName + ' ' + this.user.lastName + '" wirklich löschen?')) {
                this.saving = true;
                this.deleteUser(this.user.id)
                    .then(() => {
                        this.$router.push('/user')
                    })
                    .finally(() => {
                        this.saving = false;
                    });
            }
        }

        get toolbarTitle(): string {
            return 'Mitglied ' + (this.id === 'new' ? 'Anlegen' : 'Bearbeiten');
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

<style lang="less">
</style>