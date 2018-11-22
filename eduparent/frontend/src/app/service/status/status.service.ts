import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Status} from "../../model/status";

@Injectable({
  providedIn: 'root'
})
export class StatusService {

  constructor(private http: HttpClient) {
  }

  getStatuses(): Observable<Status[]> {
    return this.http.get<Status[]>('/api/status');
  }

  getStatusByName(statusName: string):Observable<Status>{
    return this.http.get<Status>('/api/status/' + statusName);
  }
}
