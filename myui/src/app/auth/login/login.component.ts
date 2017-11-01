import {Component, OnInit} from '@angular/core';
import {NgForm} from '@angular/forms';
import {AuthService} from '../auth.service';
import {Auth} from '../auth.model';
import {Router} from '@angular/router';
import {HeaderComponent} from "../../header/header.component";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit {

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit() {
  }

  onLogin(form: NgForm) {
      const authData: Auth = new Auth(form.value.email, form.value.password);
      this.authService.onLogin(authData).subscribe(
              (loginResult: Auth) => {
                  if (loginResult.getToken()) {
                      localStorage.setItem('currentUser',
                              JSON.stringify({
                                      username: loginResult.getEmail(),
                                      token: loginResult.getToken()
                                  }));
                      const token = this.authService.isValidToken();
                      HeaderComponent.updateMenuItems.next(true);
                      console.log(token);
                      this.router.navigate(['/list']);
                  }
              },
              (error) => console.log(error)
           );
  }
}
