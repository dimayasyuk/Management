import {Component, OnInit, TemplateRef} from '@angular/core';
import {Project} from "../../model/project";
import {ProjectService} from "../../service/project/project.service";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {Role} from "../../model/role";
import {RoleService} from "../../service/role/role.service";
import {BsModalRef, BsModalService} from "ngx-bootstrap";

@Component({
  selector: 'app-projects-list',
  templateUrl: './projects-list.component.html',
  styleUrls: ['./projects-list.component.css']
})
export class ProjectsListComponent implements OnInit {

  public projects: Project[];
  public modalRef: BsModalRef;
  public editProject: Project;

  constructor(private  projectService: ProjectService, private roleService: RoleService,
              private loadingService: Ng4LoadingSpinnerService, private modalService: BsModalService) {

  }

  ngOnInit() {
    this.loadProjects();
  }

  updateProjects(): void {
    this.loadProjects();
  }

  private loadProjects(): void {
    this.loadingService.show();
    this.projectService.getProjects().subscribe(
      project => {
        this.projects = project as Project[];
        this.loadingService.hide();
      }
    );
  }

  deleteProject(projectId: number): void {
    this.loadingService.show();
    this.projectService.deleteProject(projectId).subscribe(
      () => {
        this.updateProjects();
      }
    )
  }

  openModal(template: TemplateRef<any>,project: Project) {
    this.editProject = project;
    this.modalRef = this.modalService.show(template);
  }

  closeModal() {
    this.modalRef.hide();
  }
}
