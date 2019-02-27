<template>
    <v-card>
        <v-toolbar fixed
                   app>
            <v-toolbar-title>
                Login
            </v-toolbar-title>
            <v-spacer></v-spacer>
        </v-toolbar>
        <v-form @submit.prevent="requestToken()">
            <v-container fluid>
                <v-layout column>
                    <v-flex>
                        <v-alert
                                v-model="showError"
                                type="error"
                                dismissible
                        >
                            {{ errorMessage }}
                        </v-alert>
                    </v-flex>
                    <v-flex>
                        <v-text-field v-model="email"
                                      label="Email">
                            Email
                        </v-text-field>
                    </v-flex>
                    <v-flex align-self-end>
                        <v-btn round
                               dark
                               color="success"
                               @click="requestToken()">
                            Token anfordern
                        </v-btn>
                    </v-flex>
                </v-layout>
            </v-container>
        </v-form>
        <v-dialog
                v-model="showDialog"
                max-width="290"
        >
            <v-card>
                <v-card-title class="headline">E-Mail verschickt</v-card-title>

                <v-card-text>
                    Unser persöhnliches Skynet hat dir eine <strong>E-Mail</strong> mit deinem <strong>Login Link</strong> geschickt.
                </v-card-text>

                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn
                            color="primary"
                            flat="flat"
                            @click="showDialog = false"
                    >
                        Ok
                    </v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
    </v-card>
</template>

<script lang="ts">
    import Vue from 'vue';
    import Component from 'vue-class-component';
    import {AuthenticationAPI} from '../../api';
    import {TrackerError} from '@juggerApi'

    @Component
    export default class Login extends Vue {

        email: string = null;
        showDialog: boolean = false;
        showError: boolean = false;
        errorMessage: string = null;

        requestToken() {
            AuthenticationAPI.Instance.getAuthenticationAPI().requestLoginToken(this.email)
                .then((response) => {
                    this.showDialog = true;
                },
                (response: Response) => {
                    return response.json().then((data: TrackerError) => {
                        this.showError = true;
                        this.errorMessage = data.message;
                    });
                })
                .catch(() => {
                    this.showError = true;
                    this.errorMessage = "E-Mail nicht gefunden";
                })
        }

    }
</script>

<style scoped>

</style>
