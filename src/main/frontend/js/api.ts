import {Configuration, TrainingApi, TrainingApiFactory, UserApi} from 'juggerApi'
import * as portableFetch from "portable-fetch";

export class UserAPI {
    private static _instance: UserAPI = new UserAPI();
    private _userAPI: UserApi;

    private constructor() {
        UserAPI._instance = this;
        this.init("http://localhost:8080");
    }

    public static get Instance() {
        return this._instance;
    }

    public init(host: string) {
        this._userAPI = new UserApi(null, host, portableFetch.default);
    }

    public getUserAPI(): UserApi {
        return this._userAPI;
    }
}

export class TrainingAPI {
    private static _instance: TrainingAPI = new TrainingAPI();
    private _trainingAPI: TrainingApi;

    private constructor() {
        TrainingAPI._instance = this;
        this.init("http://localhost:8080");
    }

    public static get Instance() {
        return this._instance;
    }

    public init(host: string) {
        this._trainingAPI = new TrainingApi(null, host, portableFetch.default);
    }

    public getTrainingAPI(): TrainingApi {
        return this._trainingAPI;
    }
}