import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-user-profile-blog',
  templateUrl: './user-profile-blog.component.html',
  styleUrls: ['./user-profile-blog.component.css']
})
export class UserProfileBlogComponent implements OnInit {

  public number: number;
  public numbers: number[] = [0,1,2,3,4];


  constructor() { }

  ngOnInit() {
  }

}
