import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {UserProfileListComponent} from "./user-profile-list/user-profile-list.component";
import {UserProfileBlogComponent} from "./user-profile-blog/user-profile-blog.component";
import {UserProfileBlogItemComponent} from "./user-profile-blog/user-profile-blog-item/user-profile-blog-item.component";
import {FormsModule} from "@angular/forms";
import {UserProfileRoutingModule} from "./user-profile-routing.module";
import { UserProfileComponent } from './user-profile.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    UserProfileRoutingModule
  ],
  declarations: [
    UserProfileListComponent,
    UserProfileBlogComponent,
    UserProfileBlogItemComponent,
    UserProfileComponent
  ]
})
export class UserProfileModule { }
