import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Project} from "../../model/project";

@Injectable({
  providedIn: 'root'
})
export class ProjectService {
  private baseUrl = 'http://localhost:8081/api/projects';
  constructor(private http: HttpClient) { }

  getProjects():Observable<Project[]>{
    return this.http.get<Project[]>(`${this.baseUrl}`);
  }
  saveProject(project:Project): Observable<Project>{
    return this.http.post<Project>(`${this.baseUrl}`,project);
  }
}
