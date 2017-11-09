import { Injectable } from '@angular/core';
import { Headers, Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { UserProfile } from './user-profile.model';
import { ConfigurationService } from '../shared/configuration.service';
import 'rxjs/Rx';

@Injectable()
export class UserProfileService {

    private restUrl;
    private contentHeaders;

    constructor( private http: Http, private configurationService: ConfigurationService ) {
        this.restUrl = this.configurationService.getRestApiUrl() + '/user-rest';
        this.contentHeaders = this.configurationService.getHeaders();
    }

    getUserProfile(id: number): Observable<UserProfile> {
        return this.http.get( `${this.restUrl}/user/{id}` )
            .map(
            (response: Response) => {
                const data = response.json();
                return data;
            })
            .catch(
            ( error: Response ) => {
                return Observable.throw( 'Error: getUserProfile: something went wrong with your request for id: ' + id);
            });
    }

    getUserProfileList(): Observable<UserProfile[]> {
        return this.http.get( `${this.restUrl}/users` )
            .map(
            (response: Response) => {
                const data = response.json();
                return data;
            })
            .catch(
            ( error: Response ) => {
                return Observable.throw( 'Error: getUserProfileList: something went wrong with your request' );
            });
    }
    
    getUserCommentList(): Observable<string[]> {
        return this.http.get( `${this.restUrl}/comments` )
            .map(
            (response: Response) => {
                const data = response.json();
                return data;
            })
            .catch(
            ( error: Response ) => {
                return Observable.throw( 'Error: getUserCommentList: something went wrong with your request' );
            });
    }

    saveUserProfile(userProfile: UserProfile) {
        return this.http.post( `${this.restUrl}/save`, userProfile );
    }
}
