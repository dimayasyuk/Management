import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Task} from "../../model/task";

@Injectable({
  providedIn: 'root'
})
export class TaskService {
  private baseUrl = '/api/tasks';
  constructor(private http: HttpClient) { }

  getTasks():Observable<Task[]>{
    return this.http.get<Task[]>(`${this.baseUrl}`);
  }
  saveProject(task:Task): Observable<Task>{
    return this.http.post<Task>(`${this.baseUrl}`,task);
  }
}
