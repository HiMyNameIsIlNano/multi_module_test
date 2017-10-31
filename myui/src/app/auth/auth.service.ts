import { Http, Response } from '@angular/http';
import { ConfigurationService } from '../shared/configuration.service';
import { Injectable } from '@angular/core';
import { Auth } from './auth.model';
import { UserProfileService } from '../user-profile/user-profile.service';
import { UserProfile } from '../user-profile/user-profile.model';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class AuthService {

    private restUrl;
    private contentHeaders;

    constructor( private http: Http, private configurationService: ConfigurationService, private userProfileService: UserProfileService ) {
        this.restUrl = this.configurationService.getRestApiUrl() + '/auth-rest';
        this.contentHeaders = this.configurationService.getHeaders();
    }

    onRegistration( userProfile: UserProfile ) {
        return this.userProfileService.postUserProfile( userProfile );
    }

    onLogin( authData: Auth ): Observable<Auth> {
        return this.http.post( `${this.restUrl}/auth`, authData )
            .map(
            ( response: Response ) => {
                const data = response.json();
                const result = Auth.withToken( authData.getEmail(), data.token );
                return result;
            } )
            .catch(
            ( error: Response ) => {
                return Observable.throw( 'Error: onLogin: the user ' + authData + ' does not exist' );
            } );
    }

    onLogout(): void {
        localStorage.removeItem( 'currentUser' );
    }

    isValidToken(): Observable<boolean> {
        const local = localStorage.getItem( 'currentUser' );
        const auth = Auth.fromLocalStorage( local );

        return this.http.post( `${this.restUrl}/validate-token`, auth )
            .map(
            ( response: Response ) => {
                return response.json().valid;
            } )
            .catch(
            ( error: Response ) => {
                console.log(error);
                return Observable.throw( 'Error: isValidToken: an error occurred while validating the token' );
            } );
    }
}
