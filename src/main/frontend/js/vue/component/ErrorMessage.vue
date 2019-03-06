<template>
    <v-alert
            :value="errorMessageData.isErrorShown"
            type="error"
            dismissible
    >
        {{ errorMessageData.shownErrorMessage }}
    </v-alert>
</template>

<script lang="ts">
    import Vue from 'vue';
    import Component from 'vue-class-component';
    import {TrackerError} from '@juggerApi'
    import {Prop} from "vue-property-decorator";

    @Component
    export default class ErrorMessage extends Vue {

        @Prop({default: null}) errorMessageData: ErrorMessageData;
    }

    export class ErrorMessageData {
        showError: boolean;
        errorMessage: string;

        get isErrorShown(): boolean {
            return this.showError;
        }

        get shownErrorMessage(): string {
            return this.errorMessage;
        }

        showErrorType(errorType: TrackerError.TypeEnum): void {
            this.showError = true;
            this.errorMessage = Vue.$vuetify.t('$vuetify.errors.' + errorType);
        }

        showErrorText(errorText: string): void {
            this.showError = true;
            this.errorMessage = errorText;
        }

        showTrackerError(trackerError: TrackerError): void {
            this.showError = true;
            this.errorMessage = Vue.$vuetify.t('$vuetify.errors.' + trackerError.type);
        }

        hide(): void {
            this.showError = false;
        }
    }
</script>

<style scoped>

</style>
