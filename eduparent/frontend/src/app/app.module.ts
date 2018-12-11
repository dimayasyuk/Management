import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {ModalModule} from 'ngx-bootstrap/modal';
import {BsDropdownModule} from 'ngx-bootstrap/dropdown';
import {BsDatepickerModule} from 'ngx-bootstrap/datepicker';
import { NgSelectModule } from '@ng-select/ng-select';
import { PaginationModule } from 'ngx-bootstrap';

import {AppComponent} from './app.component';
import {TasksComponent} from './component/tasks/tasks.component';
import {AuthorizationComponent} from './component/authorization/authorization.component';
import {ProjectsComponent} from './component/projects/projects.component';
import {StartComponent} from './component/start/start.component';
import {ProjectsListComponent} from './component/projects-list/projects-list.component';
import {Ng4LoadingSpinnerModule} from "ng4-loading-spinner";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {ReactiveFormsModule,FormsModule} from "@angular/forms";
import {HeaderComponent} from './component/header/header.component';
import {SelectDropDownModule} from "ngx-select-dropdown";
import {FooterComponent} from './component/footer/footer.component';
import { NewprojectComponent } from './component/newproject/newproject.component';
import { NewUserComponent } from './component/new-user/new-user.component';
import { NewTaskComponent } from './component/new-task/new-task.component';
import { EditTaskComponent } from './component/edit-task/edit-task.component';
import { EditProjectComponent } from './component/edit-project/edit-project.component';
import {InterceptorService} from "./interceptor.service";


const routes: Routes = [
  {path: 'authorization', component: AuthorizationComponent},
  {path: '', component: StartComponent},
  {path: 'projects', component: ProjectsListComponent },
  {path: 'tasks/:id', component: TasksComponent},
  {path: 'task/:id', component: ProjectsComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    TasksComponent,
    AuthorizationComponent,
    ProjectsComponent,
    StartComponent,
    ProjectsListComponent,
    HeaderComponent,
    FooterComponent,
    NewprojectComponent,
    NewUserComponent,
    NewTaskComponent,
    EditTaskComponent,
    EditProjectComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    NgSelectModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forRoot(routes),
    PaginationModule.forRoot(),
    Ng4LoadingSpinnerModule.forRoot(),
    ModalModule.forRoot(),
    BsDropdownModule.forRoot(),
    BsDatepickerModule.forRoot(),
    SelectDropDownModule,
  ],
  providers: [
    InterceptorService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: InterceptorService,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})

export class AppModule {

}
