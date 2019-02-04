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
    import {Action, State} from "vuex-class";
    import {Namespace} from "../store/namespace";
    import {TrainingState, UserState} from "../store/types";
    import User from "./User.vue";

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

    @Component
    export default class NewTraining extends Vue {
        @State(Namespace.USER.namespace) userState: UserState;
        @State(Namespace.TRAINING.namespace) trainingState: TrainingState;
        @Action('createTraining', Namespace.TRAINING) createTraining;

        openNew() {
            this.trainingState.editTraining = {
                date: "",
                type: "klein",
                participants: []
            } as Training;
        }

        datepicker;

        get trainingTypes(): string[] {
            return ["klein", "groß", "spezial"]
        }

        get users(): User[] {
            return this.userState.users;
        }
    }
</script>

<style scoped>

</style>