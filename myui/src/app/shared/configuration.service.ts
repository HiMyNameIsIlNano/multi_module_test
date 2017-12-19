import { Injectable } from '@angular/core';

@Injectable()
export class ConfigurationService {

    private serverUrl = 'http://localhost:9090';
    private appName = '';
    private apiUrl = '';
    private restApiUrl = this.serverUrl + this.appName + this.apiUrl;
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
