import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Priority} from "../../model/priority";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class PriorityService {

  constructor(private http: HttpClient) {
  }

  getPriorities(): Observable<Priority[]> {
    return this.http.get<Priority[]>('/api/priority');
  }
}
