import {Component, Input, OnInit, TemplateRef} from '@angular/core';
import {Project} from "../../model/project";
import {Task} from "../../model/task";
import {TaskService} from "../../service/task/task.service";
import {ProjectService} from "../../service/project/project.service";
import {ActivatedRoute} from "@angular/router";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {PopoverModule} from 'ngx-bootstrap/popover';
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {Subscription} from "rxjs";
import {RefreshService} from "../../service/refresh/refresh.service";
import {UserStorageService} from "../../service/user/user-storage.service";
import {Status} from "../../model/status";
import {StatusService} from "../../service/status/status.service";
import {PriorityService} from "../../service/priority/priority.service";
import {Priority} from "../../model/priority";

@Component({
  selector: 'app-tasks',
  templateUrl: './tasks.component.html',
  styleUrls: ['./tasks.component.css']
})
export class TasksComponent implements OnInit {
  public project: Project;
  public statuses: Status[];
  public priorities: Priority[];
  public tasks: Task[];
  @Input()
  public editTask: Task;
  public page: number = 1;
  public sortPriorityMode: boolean = false;
  public sortASC: boolean = false;
  public sortStatusMode: boolean = false;
  public filterStatus: boolean = false;
  public filterPriority: boolean = false;
  public status: Status = new Status();
  public priority: Priority = new Priority();
  public totalItems: number = 0;
  public modalRef: BsModalRef;
  subscriptions: Subscription[] = [];


  constructor(private taskService: TaskService, private projectService: ProjectService,
              private route: ActivatedRoute, private loadingService: Ng4LoadingSpinnerService, private modalService: BsModalService
    , private refreshService: RefreshService, private userStorage: UserStorageService, private statusService: StatusService, private priorityService: PriorityService) {
  }

  ngOnInit() {
    const id = +this.route.snapshot.paramMap.get('id');
    this.subscriptions.push(this.refreshService.getTaskUpdateObservable().subscribe(
      () => {
        this.loadCurrentTasks();
      }
    ));
    this.project = new Project();
    this.loadProjectById(id);
    this.loadStatuses();
    this.loadPriorities()
  }

  closeModal():void{
    this.modalRef.hide();
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

  loadStatuses(): void {
    this.statusService.getStatuses().subscribe(
      status => {
        this.statuses = status;
      }
    )
  }

  loadPriorities(): void {
    this.priorityService.getPriorities().subscribe(
      priority => {
        this.priorities = priority;
      }
    );
  }

  isTester(): boolean {
    return this.userStorage.isTester();
  }

  isDeveloper(): boolean {
    return this.userStorage.isDeveloper();
  }

  pageChanged(event: any): void {
    this.page = event.page;
    if (this.sortStatusMode) {
      this.sortByStatus(false);
    } else if (this.sortPriorityMode) {
      this.sortByPriority(false);
    } else if (this.filterStatus) {
      this.filterByStatus();
    }else if(this.filterPriority){
      this.filterByPriority();
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

  filterByStatus(): void {
    if (this.status.id === 0) {
      return;
    }
    this.loadingService.show();
    this.filterStatus = true;
    this.filterPriority = false;
    this.sortStatusMode = false;
    this.sortPriorityMode = false;
    this.taskService.getFilteringTasksByStatus(this.page - 1, this.project.id, this.status.id).subscribe(
      (tasks) => {
        this.totalItems = tasks.headers.page[0];
        this.tasks = tasks.body;
        this.loadingService.hide();
        this.closeModal();
      }
    );
  }

  filterByPriority(): void {
    if (this.priority.id === 0) {
      return;
    }
    this.loadingService.show();
    this.filterStatus = false;
    this.filterPriority = true;
    this.sortStatusMode = false;
    this.sortPriorityMode = false;
    this.taskService.getFilteringTasksByPriority(this.page - 1, this.project.id, this.priority.id).subscribe(
      (tasks) => {
        this.totalItems = tasks.headers.page[0];
        this.tasks = tasks.body;
        this.loadingService.hide();
        this.closeModal();
      }
    );
  }

  sortByStatus(direct: boolean): void {
    this.loadingService.show();
    this.sortStatusMode = true;
    this.sortPriorityMode = false;
    let direction: string = this.getDirection();
    if (direct) {
      direction = this.isDirection();
    }
    this.taskService.getSortingTasksByStatus(this.page - 1, this.project.id, direction).subscribe(
      tasks => {
        this.totalItems = tasks.headers.page[0];
        this.tasks = tasks.body;
        this.loadingService.hide();
      }
    );
  }

  updateTasks(): void {
    if (this.sortStatusMode) {
      this.sortByStatus(false);
    } else if (this.sortPriorityMode) {
      this.sortByPriority(false);
    } else {
      this.loadCurrentTasks();
    }
  }

  deleteTask(taskId: number):
    void {
    this.loadingService.show();
    this.taskService.deleteTask(taskId).subscribe(
      () => {
        this.updateTasks();
      }
    )
  }

  openModal(template: TemplateRef<any>, task?: Task) {
    this.editTask = task;
    this.modalRef = this.modalService.show(template);
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }
}
