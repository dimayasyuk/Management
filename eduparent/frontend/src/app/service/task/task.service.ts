import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Task} from "../../model/task";

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  constructor(private http: HttpClient) {
  }

  getTasks(): Observable<Task[]> {
    return this.http.get<Task[]>('/api/tasks');
  }

  saveTask(task: Task): Observable<Task> {
    return this.http.post<Task>('/api/tasks', task);
  }

  getTasksByProjectId(projectId: number):Observable<Task[]>{
    return this.http.get<Task[]>('/api/tasks/' + projectId);
  }

  deleteTask(taskId: number): Observable<void> {
    return this.http.delete<void>('/api/tasks/' + taskId);
  }
  getTaskById(taskId: number):Observable<Task>{
    return this.http.get<Task>('/api/tasks/id/' + taskId);
  }
}
