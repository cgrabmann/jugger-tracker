<template>
    <v-card>
        <v-toolbar fixed
                   app>
            <v-toolbar-title>{{ toolbarTitle }}</v-toolbar-title>
        </v-toolbar>
        <v-form>
            <v-container fluid>
                <v-layout column
                          justify-space-around>
                    <v-flex>
                        <ErrorMessage v-model="errorMessageData"/>
                    </v-flex>
                    <v-flex>
                        <v-menu :disabled="!hasTrainerRights"
                                lazy
                                :close-on-content-click="true"
                                transition="scale-transition"
                                full-width
                                :nudge-left="40"
                                max-width="290px">
                            <v-text-field
                                    slot="activator"
                                    label="Datum"
                                    prepend-icon="event"
                                    :disabled="!hasTrainerRights"
                                    :value="training.date"
                                    browser-autocomplete="off"
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
                                :disabled="!hasTrainerRights"
                                label="Typ"
                                browser-autocomplete="off"
                        ></v-select>
                    </v-flex>
                    <v-flex>
                        <v-select :items="users"
                                  v-model="training.participantIds"
                                  :disabled="!hasTrainerRights"
                                  chips
                                  deletable-chips
                                  small-chips
                                  item-value="id"
                                  :item-text="participantText"
                                  :item-disabled="showParticipantDisabled"
                                  label="Teilnehmer"
                                  browser-autocomplete="off"
                                  multiple>
                        </v-select>
                    </v-flex>
                    <v-flex v-if="hasTrainerRights"
                            align-self-end>
                        <v-spacer></v-spacer>
                        <v-btn v-if="id !== 'new'"
                               :loading="saving"
                               flat
                               color="error"
                               @click="removeTraining()">
                            Löschen
                        </v-btn>
                        <v-btn :loading="saving"
                               round
                               dark
                               color="success"
                               @click="saveTraining()">
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
    import {Action, Getter, State} from "vuex-class";
    import {Namespace} from "../store/namespace";
    import {TrainingState, UserState} from "../store/types";
    import User from "./User.vue";
    import {TrackerError, Training} from 'juggerApi';
    import moment from 'moment';
    import ErrorMessage, {ErrorMessageData} from "./ErrorMessage.vue";

    @Component({
        components: {ErrorMessage},
        beforeRouteEnter(to, from, next) {
            const id = to.params.id;
            if (!moment(id, 'YYYY-MM-DD', true).isValid() && id !== 'new') {
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
    export default class TrainingView extends Vue {
        @State(Namespace.USER.namespace) userState: UserState;
        @State(Namespace.TRAINING.namespace) trainingState: TrainingState;
        @Action('getUsers', Namespace.USER) getUsers;
        @Action('createTraining', Namespace.TRAINING) createTraining;
        @Action('updateTraining', Namespace.TRAINING) updateTraining;
        @Action('getTraining', Namespace.TRAINING) getTraining;
        @Action('deleteTraining', Namespace.TRAINING) deleteTraining;
        @Getter('hasTrainerRights', Namespace.USER) hasTrainerRights: boolean;

        date: string = null;
        menu: boolean = false;
        modal: boolean = false;
        id: (string | number) = null;
        saving: boolean = false;
        showTrackableDialog: boolean = false;
        errorMessageData: ErrorMessageData = null;

        openNew(id: (string | number)) {
            this.id = id;
            this.getUsers();
            if (id === "new") {
                this.trainingState.editTraining = {
                    date: moment(new Date()).format("YYYY-MM-DD"),
                    type: "klein",
                    participantIds: []
                } as Training;
            } else {
                this.getTraining(id).then(() => {
                    this.date = this.trainingState.editTraining.date;
                });
            }
        }

        correctParticipationAndSave() {
            this.trainingState.editTraining.participantIds = this.trainingState.editTraining.participantIds.filter((userId) => {
                return this.users.find((user) => user.userId == userId).trackable;
            });
            this.showTrackableDialog = false;
            this.saveTraining();
        }

        saveTraining() {
            this.saving = true;

            let promise: Promise;
            if (this.id === "new") {
                promise = this.createTraining(this.trainingState.editTraining)
            } else {
                promise = this.updateTraining({date: this.id, training: this.trainingState.editTraining})
            }

            promise.then(() => {
                    this.$router.push('/trainings');
                },
                (response: Response) => {
                    return response.json().then((data: TrackerError) => {
                        this.errorMessageData = ErrorMessageData.fromTrackerError(this.$vuetify, data);
                    });
                })
                .finally(() => {
                    this.saving = false;
                });
        }

        removeTraining() {
            if (confirm("Training vom " + this.id + " wirklich löschen?")) {
                this.saving = true;
                this.deleteTraining(this.id)
                    .then(() => {
                            this.$router.push('/trainings')
                        },
                        (response: Response) => {
                            return response.json().then((data: TrackerError) => {
                                this.errorMessageData = ErrorMessageData.fromTrackerError(this.$vuetify, data);
                            });
                        })
                    .finally(() => {
                        this.saving = false;
                    });
            }
        }

        showParticipantDisabled(user: User, fallback?: any): boolean {
            return !user.trackable;
        }

        participantText(user: User, fallback?: any): string {
            return user.firstName + " " + user.lastName;
        }

        get toolbarTitle(): string {
            return 'Training ' + (this.id === 'new' ? 'anlegen' : 'bearbeiten');
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