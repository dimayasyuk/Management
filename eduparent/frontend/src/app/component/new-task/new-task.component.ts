import {Component, Input, OnInit} from '@angular/core';
import {Task} from "../../model/task";
import {BsModalRef} from "ngx-bootstrap";
import {Priority} from "../../model/priority";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {Account} from "../../model/account";
import {PriorityService} from "../../service/priority/priority.service";
import {AccountService} from "../../service/account/account.service";
import {TaskService} from "../../service/task/task.service";
import {StatusService} from "../../service/status/status.service";
import {Project} from "../../model/project";
import {Status} from "../../model/status";
import {TasksComponent} from "../tasks/tasks.component";
import {UserStorageService} from "../../service/user/user-storage.service";
import {ProjectService} from "../../service/project/project.service";
import {Subscription} from "rxjs";
import {RefreshService} from "../../service/refresh/refresh.service";

@Component({
  selector: 'app-new-task',
  templateUrl: './new-task.component.html',
  styleUrls: ['./new-task.component.css']
})
export class NewTaskComponent implements OnInit {

  @Input()
  public modalRef: BsModalRef;
  public editTask: Task;
  public priorities: Priority[];
  public statuses: Status[];
  public accounts: Account[];
  public project: Project;
  public projects: Project[];
  public minDate = new Date(Date.now());
  projectError: boolean = true;

  constructor(private loadingService: Ng4LoadingSpinnerService, private priorityService: PriorityService,
              private accountService: AccountService, private taskService: TaskService,
              private statusService: StatusService,
              private userStorage: UserStorageService,private projectService:ProjectService,private refreshService:RefreshService) {
  }


  ngOnInit() {
    this.editTask = new Task();
    this.project = new Project();
    this.editTask.assignedId = this.userStorage.getAccount().id;
    this.project.id = 0;
    this.loadPriorities();
    this.loadAccounts();
    this.loadStatus();
    this.loadProjects();
  }

  resetProjectError(): void{
    this.projectError = true;
  }

  closeModal(): void {
    this.modalRef.hide();
  }

  addTask(): void {
    if(this.project.id === 0){
      this.projectError = false;
      return;
    }
    this.loadingService.show();
    this.editTask.created = new Date().getTime();
    this.editTask.updated = new Date().getTime();
    this.editTask.statusId = this.statuses[0].id;
    this.editTask.projectId = this.project.id;
    this.editTask.code = this.project.code + '-';
    this.editTask.reporter = this.userStorage.getAccount();

   this.taskService.saveTask(this.editTask).subscribe(
      () => {
        this.loadingService.hide();
        this.refreshService.updateTasks(true);
        this.closeModal();
        this.refreshTask();
      }
    );
  }


  loadPriorities(): void {
    this.priorityService.getPriorities().subscribe(
      priority => {
        this.priorities = priority;
        this.editTask.priorityId = this.priorities[0].id;
      }
    );
  }

  loadProjects():void{
    this.projectService.getProjects().subscribe(
      projects =>{
        this.projects = projects;
      }
    )
  }

  loadStatus(): void {
    this.statusService.getStatuses().subscribe(
      status => {
        this.statuses = status;
          this.editTask.statusId = this.statuses[0].id;
      }
    )
  }

  loadAccounts(): void {
    this.accountService.getAccounts().subscribe(
      account => {
        this.accounts = account;
      }
    );
  }

  refreshTask(): void {
    this.editTask = new Task();
  }
}
