import {Component, Input, OnInit} from '@angular/core';
import {BsModalRef} from "ngx-bootstrap";
import {Task} from "../../model/task";
import {Priority} from "../../model/priority";
import {Status} from "../../model/status";
import {Account} from "../../model/account";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {PriorityService} from "../../service/priority/priority.service";
import {AccountService} from "../../service/account/account.service";
import {TaskService} from "../../service/task/task.service";
import {StatusService} from "../../service/status/status.service";
import {TasksComponent} from "../tasks/tasks.component";

@Component({
  selector: 'app-edit-task',
  templateUrl: './edit-task.component.html',
  styleUrls: ['./edit-task.component.css']
})
export class EditTaskComponent implements OnInit {

  @Input()
  public modalRef: BsModalRef;
  @Input()
  public id: number;
  public editTask: Task;
  public priorities: Priority[];
  public statuses: Status[];
  public accounts: Account[];
  public minDate = new Date(Date.now());

  constructor(private loadingService: Ng4LoadingSpinnerService, private priorityService: PriorityService,
              private accountService: AccountService, private taskService: TaskService, private statusService: StatusService, private taskComponent: TasksComponent) {
  }

  ngOnInit() {
    this.loadTaskById();
    this.loadPriorities();
    this.loadAccounts();
    this.loadStatus();
  }

  closeModal(): void {
    this.modalRef.hide();
  }

  loadTaskById(): void {
    this.taskService.getTaskById(this.id).subscribe(
      task => {
        this.editTask = task;
        console.log(this.editTask);
      }
    )
  }

  updateTask(): void {
    this.editTask.updated = new Date().getTime();
    this.editTask.priority = null;
    this.editTask.status = null;
    this.editTask.assignee = null;
    this.taskService.saveTask(this.editTask).subscribe(
      () => {
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
      }
    );
  }

  loadStatus(): void {
    this.statusService.getStatuses().subscribe(
      status => {
        this.statuses = status as Status[];
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
