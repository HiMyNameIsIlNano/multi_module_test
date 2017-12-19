import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AuthService } from '../auth.service';
import { UserProfile } from '../../user-profile/user-profile.model';
import {Address} from "../../user-profile/address.model";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html'
})
export class RegistrationComponent implements OnInit {

  constructor(private authService: AuthService) { }

  ngOnInit() {
  }

  onRegistration(form: NgForm) {
      const userProfile = new UserProfile('http://vignette4.wikia.nocookie.net/scribblenauts/images/4/42/Crash_Test_Dummy.png/revision/latest?cb=20130309213400'
              , form.value.name
              , form.value.surname
              , form.value.nickname
              , new Address(form.value.streetName, form.value.streetNumber, form.value.city)
              , form.value.email
              , form.value.password);

      this.authService.onRegistration(userProfile)
          .subscribe((response) => {
                  console.log(response.json());
              }
      );
  }

}
