<template>
    <v-alert
            :value="isErrorShown"
            type="error"
            dismissible
            @input="reset()"
    >
        {{ shownErrorMessage }}
    </v-alert>
</template>

<script lang="ts">
    import Vue from 'vue';
    import Component from 'vue-class-component';
    import {TrackerError} from '@juggerApi'
    import {Model} from "vue-property-decorator";

    @Component
    export default class ErrorMessage extends Vue {

        @Model('change') errorMessageData: ErrorMessageData;

        reset() {
            this.errorMessageData = null
        }

        get isErrorShown(): boolean {
            return this.errorMessageData != null;
        }

        get shownErrorMessage(): string {
            return this.errorMessageData != null ? this.errorMessageData.shownErrorMessage : "";
        }
    }

    export class ErrorMessageData {
        errorMessage: string;

        public static fromTrackerError(component: Vue, trackerError: TrackerError): ErrorMessageData {
            return new ErrorMessageData(component, null, trackerError.type);
        }

        public static fromErrorText(errorMessage: string): ErrorMessageData {
            return new ErrorMessageData(null, errorMessage, null);
        }

        public static fromErrorType(component: Vue, errorType: TrackerError.TypeEnum): ErrorMessageData {
            return new ErrorMessageData(component, null, errorType);
        }

        constructor(component: Vue, errorMessage: string, errorType: TrackerError.TypeEnum) {
            if (errorType != null) {
                this.errorMessage = component.$vuetify.t('$vuetify.errors.' + errorType);
            } else {
                this.errorMessage = errorMessage;
            }
        }

        get shownErrorMessage(): string {
            return this.errorMessage;
        }
    }
</script>

<style scoped>

</style>
