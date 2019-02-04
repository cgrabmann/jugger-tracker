import {TrainingsApi, UserApi} from './api'

export class UserAPI {
    private static _instance: UserAPI = new UserAPI();
    private _userAPI: UserApi;

    private constructor() {
        UserAPI._instance = this;
    }

    public static get Instance() {
        return this._instance;
    }

    public init(host: string) {
        this._userAPI = new UserApi(null, host)
    }

    public getUserAPI(): UserApi {
        return this._userAPI;
    }
}

export class TrainingAPI {
    private static _instance: TrainingAPI = new TrainingAPI();
    private _trainingAPI: TrainingsApi;

    private constructor() {
        TrainingAPI._instance = this;
    }

    public static get Instance() {
        return this._instance;
    }

    public init(host: string) {
        this._trainingAPI = new TrainingApi(null, host)
    }

    public getTrainingAPI(): UserApi {
        return this._trainingAPI;
    }
}