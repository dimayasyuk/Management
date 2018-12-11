import {Component, OnInit, TemplateRef} from '@angular/core';
import {Project} from "../../model/project";
import {ProjectService} from "../../service/project/project.service";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {RoleService} from "../../service/role/role.service";
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {UserStorageService} from "../../service/user/user-storage.service";

@Component({
  selector: 'app-projects-list',
  templateUrl: './projects-list.component.html',
  styleUrls: ['./projects-list.component.css']
})
export class ProjectsListComponent implements OnInit {

  public projects: Project[];
  public page: number = 1;
  public totalItems: number;
  public modalRef: BsModalRef;
  public editProject: Project;

  constructor(private  projectService: ProjectService, private roleService: RoleService,
              private loadingService: Ng4LoadingSpinnerService, private modalService: BsModalService,private userStorage:UserStorageService) {

  }

  ngOnInit() {
    this.loadCurrentProjects();
  }

  updateProjects(): void {
    this.loadCurrentProjects();
  }

  pageChanged(event: any): void {
    this.page = event.page;
    this.loadCurrentProjects();
  }

  private loadCurrentProjects(): void {
    this.loadingService.show();
    this.projectService.getCurrentProjects(this.page - 1).subscribe(
      project => {
        this.totalItems = project.headers.page[0];
        this.projects = project.body;
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

  openModal(template: TemplateRef<any>, project: Project) {
    this.editProject = project;
    this.modalRef = this.modalService.show(template);
  }

  closeModal() {
    this.modalRef.hide();
  }

  isTester(): boolean {
    return this.userStorage.isTester();
  }

  isDeveloper(): boolean {
    return this.userStorage.isDeveloper();
  }
}
