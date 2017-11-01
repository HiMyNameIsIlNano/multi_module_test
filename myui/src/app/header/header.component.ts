import {Component, OnInit} from '@angular/core';
import {AuthService} from '../auth/auth.service';
import {Router} from '@angular/router';
import {Subject} from "rxjs/Subject";

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
