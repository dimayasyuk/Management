import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Attachments} from "../../model/attachment";
import {ResponseContentType} from "@angular/http";
import {map} from "rxjs/operators";
import {Status} from "../../model/status";

@Injectable({
  providedIn: 'root'
})
export class AttachmentService {

  constructor(private http: HttpClient) {
  }

  saveFile(files: File, id: string): Observable<any> {
    const formData: FormData = new FormData();
    formData.append('file', files);
    formData.append("id", id);
    return this.http.post('/api/attachments', formData);
  }

  getAttachments(id: number): Observable<Attachments[]> {
    return this.http.get<Attachments[]>('/api/attachments/' + id);
  }

  dowloadFile(id: number): Observable<any> {
    return this.http.post('/api/attachments/files/' + id, {}, {observe: 'response', responseType: 'blob'}).pipe(map(res => { return res}));
  }

  deleteAttachment(attachment: Attachments): Observable<void> {
    return this.http.delete<void>('/api/attachments/' + attachment.id);
  }

}
