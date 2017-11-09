import { Component, OnInit } from '@angular/core';
import { UserProfileService } from "../user-profile.service";

@Component( {
    selector: 'app-user-profile-blog',
    templateUrl: './user-profile-blog.component.html',
    styleUrls: ['./user-profile-blog.component.css']
} )
export class UserProfileBlogComponent implements OnInit {

    public comments: string[] = [];

    constructor( private userProfileService: UserProfileService ) { }

    ngOnInit() {
        this.userProfileService
            .getUserCommentList()
            .subscribe(
            ( comments: string[] ) => {
                this.comments = comments
            },
            ( error ) => console.log( error )
            );
    }

}
