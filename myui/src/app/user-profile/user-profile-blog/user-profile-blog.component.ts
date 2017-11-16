import {Component, OnInit} from '@angular/core';
import {UserProfileService} from "../user-profile.service";
import {UserProfileBlogEntry} from "./user-profile-blog-item/user-profile-blog-entry.model";

@Component({
  selector: 'app-user-profile-blog',
  templateUrl: './user-profile-blog.component.html',
  styleUrls: ['./user-profile-blog.component.css']
})
export class UserProfileBlogComponent implements OnInit {

  public comments: UserProfileBlogEntry[];

  constructor(private userProfileService: UserProfileService) {
  }

  ngOnInit() {
    this.userProfileService
      .getUserCommentList('user_1@email.com')
      .subscribe(
        (comments: UserProfileBlogEntry[]) => {
          console.log(comments);
          this.comments = comments;
        },
        (error) => {
          console.log(error);
          this.comments = [];
        });
  }

}
