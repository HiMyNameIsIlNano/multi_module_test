import {Component, OnInit} from '@angular/core';
import {AuthService} from '../auth/auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html'
})
export class HeaderComponent implements OnInit {

  // When the user gets logged, this value is not refreshed, but it should...
  public isLoggedUser: boolean = false;

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit() {
    this.isLoggedIn();
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
      this.router.navigate(['/']);
  }
}
