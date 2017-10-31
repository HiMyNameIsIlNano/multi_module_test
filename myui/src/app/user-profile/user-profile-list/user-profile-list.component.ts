import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { UserProfileService } from '../user-profile.service';
import { UserProfile } from '../user-profile.model';
import { AuthService } from '../../auth/auth.service';

@Component( {
    selector: 'app-user-profile-list',
    templateUrl: './user-profile-list.component.html'
} )
export class UserProfileListComponent implements OnInit {

    public userProfiles: UserProfile[];

    constructor( private userProfileService: UserProfileService, private authService: AuthService ) { }

    ngOnInit() {
        this.userProfileService
            .getUserProfileList()
            .subscribe(
            ( userProfiles: UserProfile[] ) => {
                this.userProfiles = userProfiles;
            },
            ( error ) => console.log( error )
            );
    }

    validateToken() {
        this.authService.isValidToken()
        .subscribe(
            ( result: any ) => {
                console.log( result );
            },
            ( error ) => console.log( error )
        );
    }
}
