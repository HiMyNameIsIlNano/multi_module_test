import { Injectable } from '@angular/core';

@Injectable()
export class ConfigurationService {

    private restApiUrl = 'http://localhost:9090';
    //private appName = 'angular4-backend-framework/';
    //private apiUrl = 'rest';
    // private restApiUrl = this.serverUrl + this.appName + this.apiUrl;
    private contentHeaders = new Headers();

    constructor () {
        this.contentHeaders.append( 'Accept', 'application/json' );
        this.contentHeaders.append( 'Content-Type', 'application/json' );
    }

    getHeaders(): Headers {
        return this.contentHeaders;
    }

    getRestApiUrl(): string {
        return this.restApiUrl;
    }
}
