import {Component, Input, OnInit, TemplateRef} from '@angular/core';
import {Project} from "../../model/project";
import {Task} from "../../model/task";
import {TaskService} from "../../service/task/task.service";
import {ProjectService} from "../../service/project/project.service";
import {ActivatedRoute} from "@angular/router";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {BsModalRef, BsModalService} from "ngx-bootstrap";

@Component({
  selector: 'app-tasks',
  templateUrl: './tasks.component.html',
  styleUrls: ['./tasks.component.css']
})
export class TasksComponent implements OnInit {
  public project: Project;
  public tasks: Task[];
  @Input()
  public editTask: Task;
  public page: number = 1;
  public sortPriorityMode: boolean = false;
  public sortASC: boolean = false;
  public sortStatusMode: boolean = false;
  public totalItems: number;
  public modalRef: BsModalRef;


  constructor(private taskService: TaskService, private projectService: ProjectService,
              private route: ActivatedRoute, private loadingService: Ng4LoadingSpinnerService, private modalService: BsModalService) {
  }

  ngOnInit() {
    const id = +this.route.snapshot.paramMap.get('id');
    this.project = new Project();
    this.loadProjectById(id);
  }

  loadProjectById(id: number) {
    this.loadingService.show();
    this.projectService.getProjectById(id).subscribe(
      (project) => {
        this.project = project;
        this.loadCurrentTasks();
      }
    )
  }

  pageChanged(event: any): void {
    this.page = event.page;
    if (this.sortStatusMode) {
      this.sortByStatus(false);
    } else if (this.sortPriorityMode) {
      this.sortByPriority(false);
    } else {
      this.loadCurrentTasks();
    }
  }

  private loadCurrentTasks(): void {
    this.loadingService.show();
    this.taskService.getCurrentTasks(this.page - 1, this.project.id).subscribe(
      tasks => {
        this.totalItems = tasks.headers.page[0];
        this.tasks = tasks.body;
        this.loadingService.hide();
      }
    );
  }

  isDirection(): string {
    if (this.sortASC) {
      this.sortASC = false;
      return 'DESC';
    } else {
      this.sortASC = true;
      return 'ASC';
    }
  }

  getDirection(): string {
    if (this.sortASC) {
      return 'ASC';
    } else {
      return 'DESC';
    }
  }

  sortByPriority(direct: boolean): void {
    this.sortPriorityMode = true;
    this.sortStatusMode = false;
    this.loadingService.show();
    let direction: string = this.getDirection();
    if (direct) {
      direction = this.isDirection();
    }
    this.taskService.getSortingTasksByPriotity(this.page - 1, this.project.id, direction).subscribe(
      tasks => {
        this.totalItems = tasks.headers.page[0];
        this.tasks = tasks.body;
        this.loadingService.hide();
      }
    );
  }

  sortByStatus(direct: boolean): void {
    this.sortStatusMode = true;
    this.sortPriorityMode = false;
    let direction: string = this.getDirection();
    if (direct) {
      direction = this.isDirection();
    }
    this.loadingService.show();
    this.taskService.getSortingTasksByStatus(this.page - 1, this.project.id,direction).subscribe(
      tasks => {
        this.totalItems = tasks.headers.page[0];
        this.tasks = tasks.body;
        this.loadingService.hide();
      }
    );
  }

  updateTasks() {
    if (this.sortStatusMode) {
      this.sortByStatus(false);
    } else if (this.sortPriorityMode) {
      this.sortByPriority(false);
    } else {
      this.loadCurrentTasks();
    }
  }

  deleteTask(taskId: number): void {
    this.loadingService.show();
    this.taskService.deleteTask(taskId).subscribe(
      () => {
        this.updateTasks();
      }
    )
  }

  openModal(template: TemplateRef<any>, task: Task) {
    this.editTask = task;
    this.modalRef = this.modalService.show(template);
  }
}
