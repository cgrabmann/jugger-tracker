<template>
    <v-form>
        <v-toolbar fixed app>
            <v-toolbar-title>Training Anlegen</v-toolbar-title>
            <v-spacer></v-spacer>
            <v-toolbar-items>
                <v-btn depressed
                       color="primary"
                       v-on:click="save">Speichern
                </v-btn>
            </v-toolbar-items>
        </v-toolbar>
        <v-container fluid>
            <v-layout column
                      justify-space-around>
                <v-flex>
                    <v-menu
                            lazy
                            :close-on-content-click="true"
                            transition="scale-transition"
                            full-width
                            :nudge-left="40"
                            max-width="290px"
                    >
                        <v-text-field
                                slot="activator"
                                label="Datum"
                                prepend-icon="event"
                                :value="training.date"
                                readonly
                        ></v-text-field>
                        <v-date-picker v-model="training.date"
                                       no-title
                                       autosave>
                        </v-date-picker>
                    </v-menu>
                </v-flex>
                <v-flex>
                    <v-select
                            v-model="training.type"
                            :items="trainingTypes"
                            label="Typ"
                    ></v-select>
                </v-flex>
                <v-flex>
                    <v-select :items="users"
                              v-model="training.participants"
                              item-value="id"
                              label="Teilnehmer"
                              multiple
                              return-object>
                        <template slot="selection" slot-scope="participant">
                            <v-chip
                                    :selected="participant.selected"
                                    :key="participant.id"
                                    close
                                    class="chip--select-multi"
                                    @input="participant.parent.selectItem(participant.item)"
                            >{{ participant.item.firstName }} {{ participant.item.lastName }}
                            </v-chip>
                        </template>
                        <template slot="item" slot-scope="participant">
                            {{ participant.item.firstName }} {{ participant.item.lastName }}
                        </template>
                    </v-select>
                </v-flex>
            </v-layout>
        </v-container>
    </v-form>
</template>

<script lang="ts">
    import Vue from 'vue';
    import Component from 'vue-class-component';
    import {Action, State} from "vuex-class";
    import {Namespace} from "../store/namespace";
    import {TrainingState, UserState} from "../store/types";
    import User from "./User.vue";
    import {Training} from 'juggerApi';
    import moment from 'moment';

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
    export default class TrainingView extends Vue {
        @State(Namespace.USER.namespace) userState: UserState;
        @State(Namespace.TRAINING.namespace) trainingState: TrainingState;
        @Action('getUsers', Namespace.USER) getUsers;
        @Action('createTraining', Namespace.TRAINING) createTraining;
        @Action('updateTraining', Namespace.TRAINING) updateTraining;
        @Action('getTraining', Namespace.TRAINING) getTraining;

        date = null;
        menu = false;
        modal = false;
        id;

        openNew(id: (string | number)) {
            this.id = id;
            this.getUsers();
            if (id === "new") {
                this.trainingState.editTraining = {
                    date: moment(new Date()).format("YYYY-MM-DD"),
                    type: "klein",
                    participants: []
                } as Training;
            } else {
                this.getTraining(id).then(() => {
                    this.date = this.trainingState.editTraining.date;
                });
            }
        }

        save() {
            if (this.id === "new") {
                this.createTraining(this.trainingState.editTraining)
            } else {
                this.updateTraining({date: this.id, training: this.trainingState.editTraining})
            }
        }

        get trainingTypes(): string[] {
            return Object.values(Training.TypeEnum);
        }

        get users(): User[] {
            return this.userState.users;
        }

        get training(): Training {
            return this.trainingState.editTraining;
        }
    }
</script>

<style scoped>

</style>