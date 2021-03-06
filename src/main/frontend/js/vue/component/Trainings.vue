<template>
    <v-card>
        <v-toolbar fixed app>
            <v-toolbar-title>Trainings</v-toolbar-title>
            <v-spacer></v-spacer>
            <v-btn v-if="hasTrainerRights"
                   round
                   dark
                   to="/trainings/new"
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
                                  :items="trainings"
                                  :pagination.sync="pagination"
                                  class="mb-3"
                                  hide-actions>
                        <template slot="items" slot-scope="props">
                            <tr @click="openTrainingAction(props.item)">
                                <td class="text-xs-center">{{props.item.date}}</td>
                                <td>
                                    <template v-if="props.item.type === trainingType.Klein">
                                        <v-icon color="indigo">arrow_drop_down</v-icon>
                                    </template>
                                    <template v-else-if="props.item.type === trainingType.Gro">
                                        <v-icon color="green">arrow_drop_up</v-icon>
                                    </template>
                                    <template v-else>
                                        <v-icon color="yellow">star</v-icon>
                                    </template>
                                </td>
                                <td>({{props.item.participantIds.length}})</td>
                                <td><v-icon>keyboard_arrow_right</v-icon></td>
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
    import {TrainingState} from "../store/types";
    import {Namespace} from "../store/namespace";
    import {Action, Getter, State} from "vuex-class";
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
        @Getter('hasTrainerRights', Namespace.USER) hasTrainerRights: boolean;

        private paginationData = null;

        get pagination(): {descending: boolean, rowsPerPage: number} {
            return this.paginationData;
        }

        set pagination(paginationData) {
            this.paginationData = paginationData;
        }

        get headers() {
            return [
                {
                    text: 'Datum',
                    align: 'center',
                    value: 'date'
                },
                {
                    text: 'Typ',
                    value: 'type',
                    align: 'center',
                    width: '1px',
                    sortable: false
                },
                {
                    text: '',
                    value: 'participantIds',
                    width: '1px',
                    sortable: false
                },
                {
                    text: '',
                    width: '1px',
                    sortable: false
                }
            ];
        }

        openTrainingAction(training: Training) {
            this.$router.push("/trainings/" + training.date);
        }

        load() {
            this.getTrainings()
                .then(() => {
                    this.pagination.descending = false;
                    this.pagination.rowsPerPage = -1;
                });
        }

        get trainingType(): Training.TypeEnum {
            return Training.TypeEnum
        }

        get trainings() {
            return this.trainingState.trainings;
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