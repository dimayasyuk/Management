import {Component, Input, OnInit} from '@angular/core';
import {BsModalRef} from "ngx-bootstrap";
import {Project} from "../../model/project";
import {ProjectService} from "../../service/project/project.service";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {ProjectsListComponent} from "../projects-list/projects-list.component";
import {UserStorageService} from "../../service/user/user-storage.service";
import {RefreshService} from "../../service/refresh/refresh.service";

@Component({
  selector: 'app-edit-project',
  templateUrl: './edit-project.component.html',
  styleUrls: ['./edit-project.component.css']
})
export class EditProjectComponent implements OnInit {

  @Input()
  public modalRef: BsModalRef;
  @Input()
  public id: number;
  public editProject: Project;
  errorCode: boolean = false;

  constructor(private  projectService: ProjectService,
              private loadingService: Ng4LoadingSpinnerService,private refreshService:RefreshService) {
  }

  ngOnInit() {
    this.loadProjectById();
  }

  resetErrorCode(): void {
    this.errorCode = false;
  }

  loadProjectById(): void {
    this.projectService.getProjectById(this.id).subscribe(
      project => {
        this.editProject = project;
      }
    )
  }

  closeModal(): void {
    this.modalRef.hide();
  }

  public updateProject(): void {
    this.loadingService.show();
    this.projectService.getProjectByCode(this.editProject.code).subscribe(
      project => {
        if(project.id === this.editProject.id){
          this.saveProject();
        }else if (project) {
          this.errorCode = true;
        } else {
          this.saveProject();
        }
      }
    );
  }

  saveProject(): void {
    this.projectService.saveProject(this.editProject).subscribe(() => {
        this.updateEditProject();
        this.refreshService.updateProject(true);
        this.loadingService.hide();
        this.closeModal();
      }
    );
  }

  updateEditProject(): void {
    this.editProject = new Project();
  }
}
