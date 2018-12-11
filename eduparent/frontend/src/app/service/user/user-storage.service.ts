import {Injectable} from '@angular/core';
import {TokenStorageService} from "../token/token.storage.service";
import {User} from "../../model/user";
import {Account} from "../../model/account";

const USER_KEY = 'User';
const ACCOUNT_KEY = "Account";

@Injectable({
  providedIn: 'root'
})
export class UserStorageService {

  constructor() {
  }

  public saveUser(user: User, account: Account) {
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.setItem(USER_KEY, JSON.stringify(user));
    window.sessionStorage.removeItem(ACCOUNT_KEY);
    window.sessionStorage.setItem(ACCOUNT_KEY, JSON.stringify(account));
  }

  public getUser(): User {
    const user: User = JSON.parse(sessionStorage.getItem(USER_KEY));
    return user;
  }

  public getAccount(): Account {
    const account: Account = JSON.parse(sessionStorage.getItem(ACCOUNT_KEY));
    return account;
  }

  signOut() {
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.removeItem(ACCOUNT_KEY);
  }

  public isAdmin(): boolean {
    return this.getUser().role.name === 'Admin' ? true : false;
  }

  public isManager(): boolean {
    return this.getUser().role.name === 'Project Manager' ? true : false;
  }

  public isTester(): boolean {
    return this.getUser().role.name === 'Tester' ? true : false;
  }

  public isDeveloper(): boolean {
    return this.getUser().role.name === 'Developer' ? true : false;
  }
}
