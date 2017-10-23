import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { UserProfileListComponent } from './user-profile/user-profile-list/user-profile-list.component';
import { ErrorComponent } from './error/error.component';
import { AppRoutingModule } from './app-routing/app-routing.module';
import { FormsModule } from '@angular/forms';
import { ConfigurationService } from './shared/configuration.service';
import { AuthService } from './auth/auth.service';
import { HeaderComponent } from './header/header.component';
import { DropdownDirective } from './shared/dropdown.directive';
import { UserProfileService } from './user-profile/user-profile.service';
import { LoginComponent } from './auth/login/login.component';
import { RegistrationComponent } from './auth/registration/registration.component';
import { AuthGuard } from './auth/auth-guard.service';

@NgModule({
  declarations: [
    AppComponent,
    UserProfileListComponent,
    ErrorComponent,
    LoginComponent,
    RegistrationComponent,
    HeaderComponent,
    DropdownDirective
  ],
  imports: [
    BrowserModule,
    HttpModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [ ConfigurationService, UserProfileService, AuthService, AuthGuard ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
