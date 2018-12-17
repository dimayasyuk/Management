import {Component, OnInit, TemplateRef} from '@angular/core';
import {TaskService} from "../../service/task/task.service";
import {ProjectService} from "../../service/project/project.service";
import {ActivatedRoute} from "@angular/router";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {BsDropdownModule} from 'ngx-bootstrap';
import {Task} from "../../model/task";
import {Comment} from "../../model/comment";
import {CommentService} from "../../service/comment/comment.service";
import {Account} from "../../model/account";
import {Attachments} from "../../model/attachment";
import {AttachmentService} from "../../service/attachment/attachment.service";
import {StatusService} from "../../service/status/status.service";
import {AccountService} from "../../service/account/account.service";
import {UserStorageService} from "../../service/user/user-storage.service";
import {Status} from "../../model/status";
import {Priority} from "../../model/priority";
import {PriorityService} from "../../service/priority/priority.service";

@Component({
  selector: 'app-projects',
  templateUrl: './projects.component.html',
  styleUrls: ['./projects.component.css']
})
export class ProjectsComponent implements OnInit {

  public editTask: Task;
  public files: any = File
  public comments: Comment[];
  public comment: Comment;
  public attachment: Attachments;
  public attachments: Attachments[];
  public accounts: Account[];
  public modalRef: BsModalRef;
  public statuses: Status[];
  public priorities: Priority[];
  public editMode:boolean = false;

  constructor(private taskService: TaskService, private projectService: ProjectService,
              private route: ActivatedRoute, private loadingService: Ng4LoadingSpinnerService,
              private modalService: BsModalService, private commentService: CommentService, private attachmentService: AttachmentService
    , private accountService: AccountService, private userStorage: UserStorageService, private statusService: StatusService, private priorityService: PriorityService) {
  }

  ngOnInit() {
    this.comment = new Comment();
    this.attachment = new Attachments();
    const id = +this.route.snapshot.paramMap.get('id');
    this.loadComments(id);
    this.loadTaskById(id);
    this.loadAttachments(id);
    this.loadPriorities();
    this.loadStatuses();
  }

  loadStatuses(): void {
    this.statusService.getStatuses().subscribe(
      status => {
        this.statuses = status;
      }
    )
  }

  save(): void{
    this.loadingService.show();
    this.taskService.saveTask(this.editTask).subscribe(
      task =>{
        this.editMode = false;
        this.editTask = task;
        this.loadingService.hide();
      }
    )
  }

  edit(): void{
    this.editMode = true;
  }

  loadPriorities(): void {
    this.priorityService.getPriorities().subscribe(
      priority => {
        this.priorities = priority;
      }
    );
  }

  reOpen(): void {
    if (this.editTask.status.name === 'Ready for Test') {
      console.log(this.editTask);
      this.updateStatus('Open');
    }
  }

  close(): void {
    if (this.editTask.status.name === 'Ready for Test') {
      console.log(this.editTask);
      this.editTask.closed = new Date().getTime();
      this.updateStatus('Closed');
    }
  }

  updateStatus(status: string): void {
    this.loadingService.show();
    this.statusService.getStatusByName(status).subscribe(
      stat => {
        this.editTask.statusId = stat.id;
        this.taskService.saveTask(this.editTask).subscribe(
          (tsk) => {
            this.editTask = tsk;
            this.loadingService.hide();
          }
        )
      }
    )
  }

  start(): void {
    if (this.editTask.status.name === 'Open') {
      this.editTask.assignedId = this.userStorage.getAccount().id;
      this.updateStatus('In Progress');
    }
  }

  ready(): void {
    if (this.editTask.status.name === 'In Progress') {
      this.updateStatus('Ready for Test');
    }
  }

  loadTaskById(id: number): void {
    this.loadingService.show();
    this.taskService.getTaskById(id).subscribe(
      (task) => {
        this.editTask = task;
        this.loadingService.hide();
      }
    )
  }

  updateComment(): void {
    this.comment = new Comment();
  }

  saveComment(): void {
    this.loadingService.show();
    this.comment.taskId = this.editTask.id;
    this.comment.account = this.userStorage.getAccount();
    this.comment.posted = Date.now();
    this.commentService.saveComment(this.comment).subscribe(
      () => {
        this.updateComments();
        this.updateComment();
        this.loadingService.hide();
      }
    )
  }

  loadComments(id: number): void {
    this.loadingService.show();
    this.commentService.getComments(id).subscribe(
      comments => {
        this.comments = comments;
        this.loadingService.hide();
      }
    )
  }

  loadAttachments(id: number): void {
    this.loadingService.show();
    this.attachmentService.getAttachments(id).subscribe(
      attachments => {
        this.attachments = attachments;
      }
    )

  }

  loadAccounts(): void {
    this.accountService.getAccounts().subscribe(
      accounts => {
        this.accounts = accounts;
      }
    )
  }

  updateComments(): void {
    this.loadComments(this.editTask.id);
  }

  selectFile(event) {
    const file = event.target.files[0];
    this.files = file;
  }

  updateAttachments(): void {
    this.loadAttachments(this.editTask.id);
  }

  upload(): void {
    this.attachmentService.saveFile(this.files, this.editTask.id + '').subscribe(() => {
      this.updateAttachments();
      this.files = null;
    })
  }

  dowload(attachment: Attachments): void {
    this.attachmentService.dowloadFile(attachment.id).subscribe(
      file => {
        const wnd: any = (<any>window);
        let contentDisposition: string = file.headers.get('content-disposition');
        let fileName = contentDisposition.replace('attachment;filename=', '');
        let blobData = new Blob([<any>file.body], {type: "application/octet-stream"});
        if (wnd.navigator.appVersion.toString().indexOf('.NET') > 0) {
          wnd.navigator.msSaveBlob(blobData, '');
        } else {
          let anchor = document.createElement("a");
          anchor.download = fileName;
          anchor.href = URL.createObjectURL(blobData);
          anchor.click();
        }
      }
    )
  }

  deleteAttachment(attachment: Attachments): void {
    this.attachmentService.deleteAttachment(attachment).subscribe(
      () => {
        this.updateAttachments();
      }
    )
  }

  closeModal(): void {
    this.modalRef.hide();
  }

  openModal(template: TemplateRef<any>) {
    this.loadAccounts();
    this.modalRef = this.modalService.show(template);
  }

  reAssigne(): void {
    this.loadingService.show();
    this.taskService.saveTask(this.editTask).subscribe(
      () => {
        this.loadTaskById(this.editTask.id);
        this.closeModal();
      }
    )
  }

  isTester(): boolean {
    return this.userStorage.isTester();
  }

  isDeveloper(): boolean {
    return this.userStorage.isDeveloper();
  }

  isManager(): boolean {
    return this.userStorage.isManager();
  }

  isAdmin(): boolean {
    return this.userStorage.isAdmin();
  }
}
