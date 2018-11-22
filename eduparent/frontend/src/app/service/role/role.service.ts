import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Role} from "../../model/role";

@Injectable({
  providedIn: 'root'
})
export class RoleService {
  private baseUrl = 'http://localhost:8081/api/roles';
  constructor(private http: HttpClient) { }

  getRoles():Observable<Role[]>{
    return this.http.get<Role[]>(`${this.baseUrl}`);
  }
}
