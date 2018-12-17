import { Injectable } from '@angular/core';
import {Observable, Observer} from "rxjs";
import {share } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class RefreshService {

  private taskUpdateObservable: Observable<boolean>;
  private taskUpdateObserver: Observer<boolean>;
  private projectUpdateObservable: Observable<boolean>;
  private projectUpdateObserver: Observer<boolean>;

  constructor() {
    this.taskUpdateObservable = Observable.create((observer: Observer<boolean>) => {
      this.taskUpdateObserver= observer;
    }).pipe(share());

    this.projectUpdateObservable = Observable.create((observer: Observer<boolean>) => {
      this.projectUpdateObserver= observer;
    }).pipe(share());
  }

  public getTaskUpdateObservable(): Observable<boolean> {
    return this.taskUpdateObservable;
  }

  public updateTasks(update: boolean): void {
    if (this.taskUpdateObserver) {
      this.taskUpdateObserver.next(update);
    }
  }

  public getProjectUpdateObservable(): Observable<boolean> {
    return this.projectUpdateObservable;
  }

  public updateProject(update: boolean): void {
    if (this.projectUpdateObserver) {
      this.projectUpdateObserver.next(update);
    }
  }
}
