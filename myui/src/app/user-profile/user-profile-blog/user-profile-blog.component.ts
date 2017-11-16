import { Component, OnInit } from '@angular/core';
import { UserProfileService } from "../user-profile.service";
import { UserProfileBlogModel } from "./user-profile-blog-item/user-profile-blog.model";

@Component( {
    selector: 'app-user-profile-blog',
    templateUrl: './user-profile-blog.component.html',
    styleUrls: ['./user-profile-blog.component.css']
} )
export class UserProfileBlogComponent implements OnInit {

    public comments: UserProfileBlogModel[] = [];

    constructor( private userProfileService: UserProfileService ) { }

    ngOnInit() {
        this.userProfileService
            .getUserCommentList('user_1@email.com')
            .subscribe(
            ( comments: UserProfileBlogModel[] ) => {
                this.comments = comments
            },
            ( error ) => console.log( error )
            );
    }

}
