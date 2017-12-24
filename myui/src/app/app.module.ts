import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HttpModule} from '@angular/http';

import {AppComponent} from './app.component';
import {ErrorComponent} from './error/error.component';
import {AppRoutingModule} from './app-routing.module';
import {FormsModule} from '@angular/forms';
import {ConfigurationService} from './shared/configuration.service';
import {AuthService} from './user-profile/auth.service';
import {HeaderComponent} from './header/header.component';
import {DropdownDirective} from './shared/dropdown.directive';
import {UserProfileService} from './user-profile/user-profile.service';
import {HomepageComponent} from './homepage/homepage.component';
import {UserProfileRegistrationComponent} from './user-profile/user-profile-registration/user-profile-registration.component';
import {AuthGuard} from './user-profile/auth-guard.service';
import {UserProfileModule} from "./user-profile/user-profile.module";

@NgModule({
  declarations: [
    AppComponent,
    ErrorComponent,
    HomepageComponent,
    HeaderComponent,
    DropdownDirective
  ],
  imports: [
    BrowserModule,
    HttpModule,
    FormsModule,
    AppRoutingModule,
    UserProfileModule
  ],
  providers: [ ConfigurationService, UserProfileService, AuthService, AuthGuard ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
