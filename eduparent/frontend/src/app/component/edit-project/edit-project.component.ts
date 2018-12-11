import {Component, Input, OnInit} from '@angular/core';
import {BsModalRef} from "ngx-bootstrap";
import {Project} from "../../model/project";
import {ProjectService} from "../../service/project/project.service";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {ProjectsListComponent} from "../projects-list/projects-list.component";

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

  constructor(private  projectService: ProjectService,
              private loadingService: Ng4LoadingSpinnerService,private projectComponent: ProjectsListComponent) { }

  ngOnInit() {
    this.loadProjectById();
  }

  loadProjectById(): void{
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
    this.projectService.saveProject(this.editProject).subscribe(() => {
        this.updateEditProject();
        this.projectComponent.updateProjects();
        this.loadingService.hide();
        this.closeModal();
      }
    )
  }
  updateEditProject(): void {
    this.editProject = new Project();
  }
}
