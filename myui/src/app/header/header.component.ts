import {Component, OnInit} from '@angular/core';
import {AuthService} from '../user-profile/auth.service';
import {Router} from '@angular/router';
import {Subject} from "rxjs/Subject";
import {Auth} from "../user-profile/auth.model";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html'
})
export class HeaderComponent implements OnInit {

  public isLoggedUser: boolean = false;

  public static updateMenuItems: Subject<boolean> = new Subject();

  constructor(private authService: AuthService, private router: Router) {
    HeaderComponent.updateMenuItems.subscribe(() => {
      this.isLoggedIn();
    })
  }

  ngOnInit() {
    this.isLoggedIn();
  }

  onLogin(form: NgForm) {
    const authData: Auth = new Auth(form.value.user, form.value.password);
    this.authService.onLogin(authData).subscribe(
      (loginResult: Auth) => {
        if (loginResult.getToken()) {
          localStorage.setItem('currentUser',
            JSON.stringify({
              username: loginResult.getUser(),
              token: loginResult.getToken()
            }));
          const token = this.authService.isValidToken();
          HeaderComponent.updateMenuItems.next(true);
          this.router.navigate(['/account/blog']);
        }
      },
      (error) => console.log(error)
    );
  }

  isLoggedIn() {
    this.authService.isValidToken().subscribe(
      (isValidToken: boolean) => {
        this.isLoggedUser = isValidToken;
      },
      (error) => console.log(error)
    );
  }

  onLogout() {
    this.authService.onLogout();
    HeaderComponent.updateMenuItems.next(true);
    this.router.navigate(['/']);
  }
}
