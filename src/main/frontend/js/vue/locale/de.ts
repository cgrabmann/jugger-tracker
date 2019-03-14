export default {
    dataIterator: {
        rowsPerPageText: 'Elemente pro Seite:',
        rowsPerPageAll: 'Alle',
        pageText: '{0}-{1} von {2}',
        noResultsText: 'Keine Elemente gefunden',
        nextPage: 'Nächste Seite',
        prevPage: 'Vorherige Seite'
    },
    dataTable: {
        rowsPerPageText: 'Zeilen pro Seite:'
    },
    noDataText: 'Keine Daten vorhanden',
    user: {
        roles: {
            MITGLIED: 'Mitglied',
            TRAINER: 'Trainer',
            ADMIN: 'Admin',
        }
    },
    errors: {
        USER_NOT_FOUND: "Mitglied nicht gefunden.",
        TOKEN_NOT_FOUND: "Login-Token nicht gefunden.",
        TOKEN_ALREADY_USED: "Der Login-Token wurde bereits benutzt. Bitte fordere einen neuen an.",
        TOKEN_EXPIRED: "Der Login-Token ist abgelaufen. Bitte fordere einen neuen an.",
        UNABLE_TO_SEND_AUTHENTICATION_EMAIL: "Die Login-Email konnte nicht versendet werden. Bitte erneut versuchen.",
        TRAINING_NOT_FOUND: "Training nicht gefunden.",
        USER_NOT_TRACKABLE: "Das angegebene Mitglied möchte nicht, dass seine Trainingsbeteiligung aufgezeichnet wird.",
        EMAIL_ALREADY_IN_USE: "Die angegebene Email-Adresse wird bereits benutzt.",
        INTERNAL_SERVER_ERROR: "Ein unerwarteter Fehler ist aufgetreten. Bitte kontaktiere Clemens oder Dodo."
    }
}
