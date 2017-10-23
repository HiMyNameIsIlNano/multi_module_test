import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AuthService } from '../auth.service';
import { UserProfile } from '../../user-profile/user-profile.model';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html'
})
export class RegistrationComponent implements OnInit {

  constructor(private authService: AuthService) { }

  ngOnInit() {
  }

  onRegistration(form: NgForm) {
      const userProfile = new UserProfile(form.value.name
              , form.value.surname
              , form.value.nickname
              , form.value.email
              , form.value.password);

      this.authService.onRegistration(userProfile)
          .subscribe((response) => {
                  console.log(response.json());
              }
      );
  }

}
