import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import { ModalModule } from 'ngx-bootstrap/modal';
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { BsDatepickerModule } from 'ngx-bootstrap/datepicker';

import { AppComponent } from './app.component';
import { TasksComponent } from './component/tasks/tasks.component';
import { AuthorizationComponent } from './component/authorization/authorization.component';
import { ProjectsComponent } from './component/projects/projects.component';
import { StartComponent } from './component/start/start.component';
import { ProjectsListComponent } from './component/projects-list/projects-list.component';
import {Ng4LoadingSpinnerModule} from "ng4-loading-spinner";
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import { HeaderComponent } from './component/header/header.component';


const routes:Routes = [
  {
    path: 'authorization',
    component: AuthorizationComponent
  },
  {
    path:'',
    component:StartComponent
  }
];

@NgModule({
  declarations: [
    AppComponent,
    TasksComponent,
    AuthorizationComponent,
    ProjectsComponent,
    StartComponent,
    ProjectsListComponent,
    HeaderComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(routes),
    Ng4LoadingSpinnerModule.forRoot(),
    ModalModule.forRoot(),
    BsDropdownModule.forRoot(),
    BsDatepickerModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})

export class AppModule {

}
