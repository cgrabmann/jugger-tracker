<template>
    <v-card>
        <v-toolbar fixed
                   app>
            <v-toolbar-title>
                Login
            </v-toolbar-title>
            <v-spacer></v-spacer>
        </v-toolbar>
        <v-form>
            <v-container fluid>
                <v-layout column>
                    <v-flex>
                        <v-text-field v-model="email"
                                      label="Email">
                            Email
                        </v-text-field>
                    </v-flex>
                    <v-flex align-self-end>
                        <v-btn round
                               dark
                               color="indigo" @click="requestToken()">
                            Token anfordern
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
    import {AuthenticationAPI} from '../../api';

    @Component
    export default class Login extends Vue {

        email: string;

        requestToken() {
            AuthenticationAPI.Instance.getAuthenticationAPI().requestLoginToken(this.email);
        }

    }
</script>

<style scoped>

</style>