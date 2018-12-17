import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Account} from "../../model/account";

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  constructor(private http: HttpClient) {
  }

  getAccounts(): Observable<Account[]> {
    return this.http.get<Account[]>('api/accounts');
  }

  saveAccount(account: Account): Observable<Account> {
    return this.http.post<Account>('api/accounts', account);
  }

  getAccountByUserId(id: number): Observable<Account>{
    return this.http.get<Account>('api/accounts/' + id);
  }

  getAccountByEmail(email: string): Observable<Account>{
    return this.http.get<Account>('api/accounts/email/' + email);
  }
}
