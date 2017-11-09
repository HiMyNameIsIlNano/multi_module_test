import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-user-profile-blog-item',
  templateUrl: './user-profile-blog-item.component.html',
  styleUrls: ['./user-profile-blog-item.component.css']
})
export class UserProfileBlogItemComponent implements OnInit {

  @Input() text: string = '';
    
  constructor() { }

  ngOnInit() {
  }

}
