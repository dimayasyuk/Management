import {Component, Input, OnInit} from '@angular/core';
import {Project} from "../../model/project";
import {ProjectService} from "../../service/project/project.service";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {BsModalRef} from "ngx-bootstrap";
import {ProjectsListComponent} from "../projects-list/projects-list.component";
import {UserStorageService} from "../../service/user/user-storage.service";
import {RefreshService} from "../../service/refresh/refresh.service";

@Component({
  selector: 'app-newproject',
  templateUrl: './newproject.component.html',
  styleUrls: ['./newproject.component.css']
})
export class NewprojectComponent implements OnInit {

  public editProject: Project;
  @Input()
  public modalRef: BsModalRef;
  errorCode: boolean = false;

  constructor(private  projectService: ProjectService,
              private loadingService: Ng4LoadingSpinnerService,
              private userStorage: UserStorageService,private refreshService:RefreshService) {
  }

  ngOnInit() {
    this.editProject = new Project();
  }

   addProject(): void {
    this.loadingService.show();
    this.editProject.reporter = this.userStorage.getAccount();
    this.projectService.getProjectByCode(this.editProject.code).subscribe(
      project=>{
        if(project){
          this.errorCode = true;
        }else{
          this.saveProject();
        }
      }
    );

  }

  resetErrorCode():void{
    this.errorCode = false;
  }

  saveProject(): void{
    this.projectService.saveProject(this.editProject).subscribe(() => {
        this.loadingService.hide();
        this.refreshService.updateProject(true);
        this.closeModal();
        this.updateEditProject();
      }
    );
  }

  updateEditProject(): void {
    this.editProject = new Project();
  }

  closeModal():void{
    this.modalRef.hide();
  }
}
