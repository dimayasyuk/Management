import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {AuthService} from "../../service/auth/auth.service";
import {TokenStorageService} from "../../service/token/token.storage.service";
import {User} from "../../model/user";
import {UserService} from "../../service/user/user.service";
import {UserStorageService} from "../../service/user/user-storage.service";
import {AccountService} from "../../service/account/account.service";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";

@Component({
  selector: 'app-authorization',
  templateUrl: './authorization.component.html',
  styleUrls: ['./authorization.component.css']
})
export class AuthorizationComponent implements OnInit {

  user: User = new User();

  constructor(private router: Router, private authService: AuthService,
              private token: TokenStorageService, private userService: UserService,
              private acccountService: AccountService, private userStorage: UserStorageService, private loadingService: Ng4LoadingSpinnerService,) {
  }

  login(): void {
    this.loadingService.show();
      this.authService.attemptAuth(this.user.login, this.user.password).subscribe(
        data => {
          if (data) {
            this.token.saveToken(data.token);
            this.userService.getUserByToken(data.token).subscribe(
              user => {
                if (user) {
                  this.acccountService.getAccountByUserId(user.id).subscribe(
                    account => {
                      if (account) {
                        this.userStorage.saveUser(user, account);
                        this.router.navigate(['/projects']);
                        this.loadingService.hide();
                      }
                    }
                  );
                }
              }
            );
          }
        }
      );
  }


  ngOnInit() {
  }

}
