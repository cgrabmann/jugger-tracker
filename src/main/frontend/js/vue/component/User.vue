<template>
    <v-card id="user">
        <v-toolbar fixed
                   app>
            <v-toolbar-title>{{ toolbarTitle }}</v-toolbar-title>
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
                                      browser-autocomplete="off"
                                      required></v-text-field>
                    </v-flex>
                    <v-flex>
                        <v-text-field v-model="user.lastName"
                                      :counter="20"
                                      :rules="nameRules"
                                      label="Nachname"
                                      browser-autocomplete="off"
                                      required></v-text-field>
                    </v-flex>
                    <v-flex>
                        <v-text-field v-model="user.email"
                                      :counter="50"
                                      :rules="emailRules"
                                      label="Email"
                                      type="email"
                                      required></v-text-field>
                    </v-flex>
                    <v-flex>
                        <v-select
                                v-model="user.role"
                                :items="userRoles"
                                item-text="text"
                                item-value="value"
                                label="Rolle"
                                browser-autocomplete="off"
                        ></v-select>
                    </v-flex>
                    <v-flex>
                        <v-switch
                                v-model="user.trackable"
                                label="Trainingsbeteiligung aufzeichnen"
                                color="primary"></v-switch>
                    </v-flex>
                    <v-flex align-self-end>
                        <v-spacer></v-spacer>
                        <v-btn v-if="!!user.id"
                               :loading="saving"
                               flat
                               color="error"
                               @click="removeUser()">
                            Löschen
                        </v-btn>
                        <v-btn :loading="saving"
                               round
                               dark
                               color="success"
                               @click="saveUser()">
                            <v-icon left>save</v-icon>
                            Speichern
                        </v-btn>
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
    import {User, UserData} from 'juggerApi'

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
            return 'Mitglied ' + (this.id === 'new' ? 'anlegen' : 'bearbeiten');
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

        get userRoles(): string[] {
            return Object.values(UserData.RoleEnum).map(v => {
                return {
                    value: v,
                    text: this.$vuetify.t('$vuetify.user.roles.' + v)
                }
            });
        }

        lengthRule(length: number): any {
            return value => !!value && value.length <= length || 'Maximal ' + length + ' Zeichen erlaubt';
        }
    }
</script>

<style lang="less">
</style>