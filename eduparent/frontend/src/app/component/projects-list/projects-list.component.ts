import {Component, OnInit, TemplateRef} from '@angular/core';
import {Project} from "../../model/project";
import {ProjectService} from "../../service/project/project.service";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {BsModalService, BsModalRef} from "ngx-bootstrap";
import {Role} from "../../model/role";
import {RoleService} from "../../service/role.service";

@Component({
  selector: 'app-projects-list',
  templateUrl: './projects-list.component.html',
  styleUrls: ['./projects-list.component.css']
})
export class ProjectsListComponent implements OnInit {

  public modalRef: BsModalRef;
  public projects: Project[];
  public roles: Role[];
  public editProject: Project = new Project();

  constructor(private  projectService: ProjectService, private roleService: RoleService,
              private loadingService: Ng4LoadingSpinnerService,
              private modalService: BsModalService) {

  }

  ngOnInit() {
    this._loadProjects();
    this._loadRoles();
  }

  _updateProjects(): void {
    this._loadProjects();
  }

  _openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
  }

  _closeModal() {
    this.modalRef.hide();
  }
  _updateEditProject():void{
    this.editProject = new Project();
  }
  public _addProject(): void {
    this.loadingService.show();
    this.editProject.createdId = 2;
    this.projectService.saveProject(this.editProject).subscribe(() => {
        this._updateProjects();
        this._closeModal();
        this._updateEditProject();
        this.loadingService.hide();
      }
    )

  }

  private _loadProjects(): void {
    this.loadingService.show();
    this.projectService.getProjects().subscribe(
      project => {
        this.projects = project as Project[];
        this.loadingService.hide();
      }
    );
  }

  private _loadRoles(): void {
    this.loadingService.show();
    this.roleService.getRoles().subscribe(
      role => {
        this.roles = role as Role[];
        this.loadingService.hide();
      }
    );
  }
}
