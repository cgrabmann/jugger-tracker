<template>
    <div>
        <p>Testtest ({{ numTrainings }})</p>
        <v-list v-for="training in trainings">
            <v-list-tile-title v-text="training.date"></v-list-tile-title>
        </v-list>
    </div>
</template>

<script lang="ts">
    import Vue from 'vue';
    import Component from 'vue-class-component';
    import {TrainingState} from "../store/types";
    import {Namespace} from "../store/namespace";
    import {Action, State} from "vuex-class";

    @Component({
        beforeRouteEnter(to, from, next) {
            next(vm => {
                vm.load();
                next();
            })
        },
        beforeRouteUpdate(to, from, next) {
            this.load();
            next();
        }
    })
    export default class Trainings extends Vue {
        @State(Namespace.TRAINING.namespace) trainingState: TrainingState;
        @Action('getTrainings', Namespace.TRAINING) getTrainings: any;

        load() {
            console.log("Loading trainings...");
            this.getTrainings();
        }

        get users() {
            return this.trainingState.trainings;
        }

        get numTrainings() {
            return this.trainingState.trainings.length
        }
    }
</script>

<style scoped>

</style>