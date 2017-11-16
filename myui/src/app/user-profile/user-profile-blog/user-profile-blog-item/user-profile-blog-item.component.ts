import { Component, OnInit, Input } from '@angular/core';
import { UserProfileBlogModel } from "./user-profile-blog.model";

@Component({
  selector: 'app-user-profile-blog-item',
  templateUrl: './user-profile-blog-item.component.html',
  styleUrls: ['./user-profile-blog-item.component.css']
})
export class UserProfileBlogItemComponent implements OnInit {

  @Input() comment: UserProfileBlogModel;
    
  constructor() { }

  ngOnInit() {
  }

}
