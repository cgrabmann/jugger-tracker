<template>
    <v-form>
        <v-date-picker
                v-model="datepicker"
                label="Date"
                :date-format="date => new Date(date).toDateString()"
                required
        ></v-date-picker>
        <v-select
                v-model="training.type"
                :items="trainingTypes"
                label="Date"
        ></v-select>
        <v-list v-for="user in users">
            <v-list-tile-title v-text="user.firstName"></v-list-tile-title>
        </v-list>
        <v-button flat
                  v-on:click="save">
            Speichern
        </v-button>
    </v-form>
</template>

<script lang="ts">
    import Vue from 'vue';
    import Component from 'vue-class-component';
    import {Training} from './api'
    import {State} from "vuex-class";
    import {Namespace} from "../store/namespace";
    import {UserState} from "../store/types";
    import User from "./User.vue";

    @Component
    export default class NewTraining extends Vue {
        @State(Namespace.USER.namespace) userState: UserState;

        datepicker;
        users: User[];
        training: Training = {
            date: "",
            type: "klein",
            participants: []
        };

        created() {
            this.fetchUsers();
        }

        get trainingTypes(): string[] {
            return ["klein", "groß", "spezial"]
        }

        get users(): User[] {
            return this.userState.users;
        }

        fetchUsers() {
            this.error = this.post = null
            this.loading = true
            // replace `getPost` with your data fetching util / API wrapper
            getPost(this.$route.params.id, (err, post) => {
                this.loading = false
                if (err) {
                    this.error = err.toString()
                } else {
                    this.post = post
                }
            })
        }

        save() {
            console.log(this.training);
        }
    }
</script>

<style scoped>

</style>