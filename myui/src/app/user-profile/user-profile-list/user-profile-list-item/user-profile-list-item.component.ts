import { Component, OnInit, Input } from '@angular/core';
import { UserProfile } from "../../user-profile.model";

@Component( {
    selector: 'app-user-profile-list-item',
    templateUrl: './user-profile-list-item.component.html',
    styles: [ './user-profile-list-item.component.css' ]
} )
export class UserProfileListItemComponent implements OnInit {

    @Input() userProfile: UserProfile;

    constructor() { }

    ngOnInit() {
    }
}
