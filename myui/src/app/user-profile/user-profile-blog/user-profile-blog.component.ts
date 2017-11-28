import {Component, OnInit} from '@angular/core';
import {UserProfileService} from "../user-profile.service";
import {UserProfileBlogEntry} from "./user-profile-blog-item/user-profile-blog-item.model";

@Component({
  selector: 'app-user-profile-blog',
  templateUrl: './user-profile-blog.component.html',
  styleUrls: ['./user-profile-blog.component.css']
})
export class UserProfileBlogComponent implements OnInit {

  public comments: UserProfileBlogEntry[];
  public hasComments: boolean;

  constructor(private userProfileService: UserProfileService) {
  }

  ngOnInit() {
    this.userProfileService
      .getUserCommentList('User_1')
      .subscribe(
        (comments: UserProfileBlogEntry[]) => {
          console.log(comments);
          this.comments = comments;
          this.hasComments = comments.length > 0;
        },
        (error) => {
          console.log(error);
          this.comments = [];
        });
  }

}
