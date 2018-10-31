import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AccountService {
  private baseUrl = '/api/accounts';
  constructor(private http: HttpClient) { }

  getAccounts():Observable<Account[]>{
    return this.http.get<Account[]>(`${this.baseUrl}`);
  }
  saveAccount(account:Account): Observable<Account>{
    return this.http.post<Account>(`${this.baseUrl}`,account);
  }
}
