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
  @Input()
  public project: Project;
  public minDate = new Date(Date.now());

  constructor(private loadingService: Ng4LoadingSpinnerService, private priorityService: PriorityService,
              private accountService: AccountService, private taskService: TaskService, private statusService: StatusService, private taskComponent: TasksComponent) {
  }

  ngOnInit() {
    this.editTask = new Task();
    this.loadPriorities();
    this.loadAccounts();
    this.loadStatus();
  }

  closeModal(): void {
    this.modalRef.hide();
  }

  addTask(): void {
    this.loadingService.show();
    this.editTask.created = new Date().getTime();
    this.editTask.updated = new Date().getTime();
    this.editTask.statusId = this.statuses[0].id;
    this.editTask.projectId = this.project.id;
    this.editTask.code = this.project.code;

    this.taskService.saveTask(this.editTask).subscribe(
      () => {
        this.loadingService.hide();
        this.taskComponent.updateTasks();
        this.closeModal();
        this.refreshTask();
      }
    )
  }

  loadPriorities(): void {
    this.priorityService.getPriorities().subscribe(
      priority => {
        this.priorities = priority as Priority[];
        this.editTask.priority = this.priorities[0];
      }
    );
  }

  loadStatus(): void {
    this.statusService.getStatuses().subscribe(
      status => {
        this.statuses = status as Status[];
          this.editTask.status = this.statuses[0];
      }
    )
  }

  loadAccounts(): void {
    this.accountService.getAccounts().subscribe(
      account => {
        this.accounts = account as Account[];
      }
    );
  }

  refreshTask(): void {
    this.editTask = new Task();
  }
}
