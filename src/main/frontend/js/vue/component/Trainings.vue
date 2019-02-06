<template>
    <div>
        <v-toolbar>
            <v-toolbar-title>Trainings</v-toolbar-title>
            <v-spacer></v-spacer>
            <v-toolbar-items>
                <v-btn depressed
                       color="primary"
                       to="/trainings/new">Training anlegen
                </v-btn>
            </v-toolbar-items>
        </v-toolbar>
        <v-container fluid>
            <v-layout column
                      justify-space-around>
                <v-flex>
                    <v-data-table :headers="headers"
                                  :items="trainings"
                                  v-bind:pagination.sync="pagination"
                                  class="elevation-1">
                        <template slot="items" slot-scope="props">
                            <tr>
                                <td class="text-xs-left">{{props.item.date}}</td>
                                <td class="text-xs-left">{{props.item.type}}</td>
                                <td>({{props.item.participants.length}}) {{participantList(props.item.participants)}}</td>
                                <td class="text-xs-right">
                                    <v-btn v-on:click="openTrainingAction(props.item)"
                                           fab small depressed
                                           color="primary">
                                        <v-icon>edit</v-icon>
                                    </v-btn>
                                    <v-btn v-on:click="deleteTrainingAction(props.item)"
                                           fab small depressed
                                           color="secondary">
                                        <v-icon>delete</v-icon>
                                    </v-btn>
                                </td>
                            </tr>
                        </template>
                    </v-data-table>
                </v-flex>
            </v-layout>
        </v-container>
    </div>
</template>

<script lang="ts">
    import Vue from 'vue';
    import Component from 'vue-class-component';
    import {TrainingState} from "../store/types";
    import {Namespace} from "../store/namespace";
    import {Action, State} from "vuex-class";
    import {Training, User} from "juggerApi";

    @Component({
        beforeRouteEnter(to, from, next) {
            next(vm => {
                vm.load();
                next();
            })
        }
    })
    export default class Trainings extends Vue {
        @State(Namespace.TRAINING.namespace) trainingState: TrainingState;
        @Action('getTrainings', Namespace.TRAINING) getTrainings: any;
        @Action('deleteTraining', Namespace.TRAINING) deleteTraining: any;

        paginationData: null;

        get pagination() {
            return this.paginationData;
        }

        set pagination(paginationData) {
            this.paginationData = paginationData;
        }

        get headers() {
            return [
                {
                    text: 'Datum',
                    align: 'left',
                    value: 'date'
                },
                {
                    text: 'Typ',
                    value: 'type'
                },
                {
                    text: 'Teilnehmer',
                    value: 'participants',
                    sortable: false
                },
                {
                    text: '',
                    sortable: false
                }
            ];
        }

        openTrainingAction(training: Training) {
            this.$router.push("/trainings/" + training.date);
        }

        deleteTrainingAction(training: Training) {
            if (confirm("Training vom " + training.date + " wirklich löschen?")) {
                this.deleteTraining(training.date).then(() => {
                    this.getTrainings();
                });
            }
        }

        load() {
            this.getTrainings().then(() => {
                this.paginationData.descending = true;
            });
        }

        get trainings() {
            return this.trainingState.trainings;
        }

        get numTrainings() {
            return this.trainingState.trainings.length
        }

        participantList(participants: User[]) {
            return participants.map(participant => participant.firstName).join(', ')
        }
    }
</script>

<style scoped>

</style>