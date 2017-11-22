import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-user-profile-blog-item',
  templateUrl: './user-profile-blog-item.component.html',
  styleUrls: ['./user-profile-blog-item.component.css']
})
export class UserProfileBlogItemComponent implements OnInit {

  @Input() text: string;
  @Input() topic: string;

  constructor() { }

  ngOnInit() {
  }

}
