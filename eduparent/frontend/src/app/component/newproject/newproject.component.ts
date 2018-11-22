import {Component, Input, OnInit} from '@angular/core';
import {Project} from "../../model/project";
import {Account} from "../../model/account";
import {ProjectService} from "../../service/project/project.service";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {BsModalRef} from "ngx-bootstrap";
import {ProjectsListComponent} from "../projects-list/projects-list.component";

@Component({
  selector: 'app-newproject',
  templateUrl: './newproject.component.html',
  styleUrls: ['./newproject.component.css']
})
export class NewprojectComponent implements OnInit {

  @Input()
  public editMode = false;
  @Input()
  public editProject: Project;
  @Input()
  public modalRef: BsModalRef;

  constructor(private  projectService: ProjectService,
              private loadingService: Ng4LoadingSpinnerService,private projectComponent: ProjectsListComponent) {
  }

  ngOnInit() {
    this.editProject = new Project();
  }

  public addProject(): void {
    this.loadingService.show();
    this.editProject.reporter.id = 16;
    this.projectService.saveProject(this.editProject).subscribe(() => {
        this.updateEditProject();
        this.projectComponent.updateProjects();
        this.loadingService.hide();
        this.closeModal();
        this.editMode = false;
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
