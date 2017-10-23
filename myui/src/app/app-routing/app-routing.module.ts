import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { ErrorComponent } from '../error/error.component';
import { LoginComponent } from '../auth/login/login.component';
import { RegistrationComponent } from '../auth/registration/registration.component';
import { UserProfileListComponent } from '../user-profile/user-profile-list/user-profile-list.component';
import { AuthGuard } from '../auth/auth-guard.service';

const appRoutes: Routes = [
    { path: '', redirectTo: '/login', pathMatch: 'full' },
    { path: 'list', component: UserProfileListComponent, canActivate: [AuthGuard] },
    { path: 'login', component: LoginComponent },
    { path: 'register', component: RegistrationComponent },
    { path: 'blog', component: ErrorComponent, data: {message: 'Page in construction!'}, canActivate: [AuthGuard] },
    { path: 'events', component: ErrorComponent, data: {message: 'Page in construction!'}, canActivate: [AuthGuard] },
    { path: '**', component: ErrorComponent, data: {message: 'Page not found!'} },
];

@NgModule({
  imports: [
    RouterModule.forRoot(appRoutes)
  ],
  exports: [RouterModule],
  declarations: []
})
export class AppRoutingModule { }
