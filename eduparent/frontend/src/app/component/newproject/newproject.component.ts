import {Component, Input, OnInit} from '@angular/core';
import {Project} from "../../model/project";
import {ProjectService} from "../../service/project/project.service";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {BsModalRef} from "ngx-bootstrap";
import {ProjectsListComponent} from "../projects-list/projects-list.component";
import {UserStorageService} from "../../service/user/user-storage.service";

@Component({
  selector: 'app-newproject',
  templateUrl: './newproject.component.html',
  styleUrls: ['./newproject.component.css']
})
export class NewprojectComponent implements OnInit {

  public editProject: Project;
  @Input()
  public modalRef: BsModalRef;

  constructor(private  projectService: ProjectService,
              private loadingService: Ng4LoadingSpinnerService,private projectComponent: ProjectsListComponent,
              private userStorage: UserStorageService) {
  }

  ngOnInit() {
    this.editProject = new Project();
  }

   addProject(): void {
    this.loadingService.show();
    this.editProject.reporter = this.userStorage.getAccount();
    this.projectService.saveProject(this.editProject).subscribe(() => {
        this.projectComponent.updateProjects();
        this.loadingService.hide();
        this.closeModal();
        this.updateEditProject();
      }
    )
  }

  updateEditProject(): void {
    this.editProject = new Project();
  }

  closeModal():void{
    this.modalRef.hide();
  }
}
