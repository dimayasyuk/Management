import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Comment} from "../../model/comment";

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  constructor(private http: HttpClient) {
  }

  getComments(id: number): Observable<Comment[]> {
    return this.http.get<Comment[]>('/api/comments/' + id);
  }

  saveComment(comment: Comment): Observable<Comment> {
    return this.http.post<Comment>('/api/comments', comment);
  }
}
