import { Injectable } from '@angular/core';
import { Headers, Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/Rx';
import { ConfigurationService } from './configuration.service';


@Injectable()
export class LoopbackService {

    private baseUrl;
    private contentHeaders;
    private seconds = 180; // 3 Minutes

    constructor( private http: Http, private configurationService: ConfigurationService ) {
        this.baseUrl = this.configurationService.getRestApiUrl() + '/loopback';
        this.contentHeaders = this.configurationService.getHeaders();
    }

    testRestInterface( text: string ) {
        return this.http.get( `${this.baseUrl}/${text}` )
            .map(( response: Response ) => {
                return response;
            } )
            .catch(( error: Response ) => {
                return Observable.throw( 'Error: testRestInterface: something went wrong with your request' );
            } );
    }

    testRestInterfaceWithPolling( text: string ) {
        return Observable.interval( this.seconds * 1000 ).startWith( 0 )
            .switchMap(() =>
                this.http.get( `${this.baseUrl}/${text}` )
                    .map(( response: Response ) => {
                        return response;
                    })
                    .catch(( error: Response ) => {
                        return Observable.throw( 'Error: testRestInterface: something went wrong with your request' );
                    })
            );

        /*
         * const subscribe = source.subscribe(val => console.log(val));
         * return Observable.interval( this.seconds * 1000 ).startWith( 0 )
            .switchMap(() =>
                this.http.get( `${this.baseUrl}/${text}` )
                    .map(( response: Response ) => {
                        return response;
                    })
                    .catch(( error: Response ) => {
                        return; Observable.throw( 'Error: testRestInterface: something went wrong with your request' );
                    }));
                    */
    }
}
