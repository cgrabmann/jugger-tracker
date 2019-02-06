import {BindingOptions} from 'vuex-class/lib/bindings';

export class Namespace {
    public static readonly USER: BindingOptions = {namespace: 'UserModule'};
    public static readonly TRAINING: BindingOptions = {namespace: 'TrainingModule'};
}