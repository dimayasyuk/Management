import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Project} from "../../model/project";

@Injectable({
  providedIn: 'root'
})
export class ProjectService {
  constructor(private http: HttpClient) {
  }

  getProjects(): Observable<Project[]> {
    return this.http.get<Project[]>('/api/projects');
  }

  getProjectByCode(code: string): Observable<Project> {
    return this.http.get<Project>('/api/projects/code/' + code);
  }

  getCurrentProjects(page: number): Observable<any> {
    return this.http.get<any>('/api/projects/page/' + page);
  }

  getNumberOfProjects(): Observable<number>{
    return this.http.get<number>('/api/projects/number');
  }

  getProjectById(id: number): Observable<Project> {
    return this.http.get<Project>('/api/projects/' + id);
  }

  saveProject(project: Project): Observable<Project> {
    return this.http.post<Project>('/api/projects', project);
  }

  deleteProject(projectId: number): Observable<void> {
    return this.http.delete<void>('/api/projects/' + projectId);
  }
}
