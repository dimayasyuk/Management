import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../../model/user";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  saveUser(user:User): Observable<User>{
    return this.http.post<User>('/api/users',user);
  }

  getUserByLogin(login:string): Observable<User>{
    return this.http.get<User>('/api/users/' + login);
  }
}
