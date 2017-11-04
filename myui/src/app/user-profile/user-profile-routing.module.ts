import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from "@angular/router";
import {AuthGuard} from "../auth/auth-guard.service";
import {UserProfileListComponent} from "./user-profile-list/user-profile-list.component";
import {UserProfileBlogComponent} from "./user-profile-blog/user-profile-blog.component";
import {ErrorComponent} from "../error/error.component";
import {UserProfileComponent} from "./user-profile.component";

const userProfileRoutes: Routes = [
  { path: 'account', component: UserProfileComponent, children: [
    { path: 'friends', component: UserProfileListComponent, canActivate: [AuthGuard] },
    { path: 'blog', component: UserProfileBlogComponent, canActivate: [AuthGuard] },
    { path: 'events', component: ErrorComponent, data: {message: 'Page in construction!'}, canActivate: [AuthGuard] },
    { path: '**', component: ErrorComponent, data: {message: 'Page not found!'} }
  ]}

  /*{ path: 'friends', component: UserProfileListComponent, canActivate: [AuthGuard] },
  { path: 'blog', component: UserProfileBlogComponent, canActivate: [AuthGuard] },
  { path: 'events', component: ErrorComponent, data: {message: 'Page in construction!'}, canActivate: [AuthGuard] },
  { path: '**', component: ErrorComponent, data: {message: 'Page not found!'} }*/
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(userProfileRoutes)
  ],
  exports: [ RouterModule ],
  declarations: []
})
export class UserProfileRoutingModule {
}
