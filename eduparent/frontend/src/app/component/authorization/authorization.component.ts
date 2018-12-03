import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {AuthService} from "../../service/auth/auth.service";
import {TokenStorageService} from "../../service/token/token.storage.service";

@Component({
  selector: 'app-authorization',
  templateUrl: './authorization.component.html',
  styleUrls: ['./authorization.component.css']
})
export class AuthorizationComponent implements OnInit {

  constructor(private router: Router, private authService: AuthService, private token: TokenStorageService) {
  }

  username: string;
  password: string;
  login(): void {
    this.authService.attemptAuth(this.username, this.password).subscribe(
      data => {
        this.token.saveToken(data.token);
        this.router.navigate(['projects']);
      }
    );
  }


  ngOnInit() {
  }

}
