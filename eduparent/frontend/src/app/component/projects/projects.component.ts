import {Component, OnInit, TemplateRef} from '@angular/core';
import {TaskService} from "../../service/task/task.service";
import {ProjectService} from "../../service/project/project.service";
import {ActivatedRoute} from "@angular/router";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {Task} from "../../model/task";
import {Comment} from "../../model/comment";
import {CommentService} from "../../service/comment/comment.service";
import {Account} from "../../model/account";
import {Attachments} from "../../model/attachment";
import {AttachmentService} from "../../service/attachment/attachment.service";
import {StatusService} from "../../service/status/status.service";
import {AccountService} from "../../service/account/account.service";

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

  constructor(private taskService: TaskService, private projectService: ProjectService,
              private route: ActivatedRoute, private loadingService: Ng4LoadingSpinnerService, private statusService: StatusService,
              private modalService: BsModalService, private commentService: CommentService, private attachmentService: AttachmentService
    , private accountService: AccountService) {
  }

  ngOnInit() {
    this.comment = new Comment();
    this.attachment = new Attachments();
    const id = +this.route.snapshot.paramMap.get('id');
    this.loadComments(id);
    this.loadTaskById(id);
    this.loadAttachments(id);
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

  updateStatusTask(statusName: string): void {
    this.statusService.getStatusByName(statusName).subscribe(
      status => {
        this.editTask.statusId = status.id;
        this.taskService.saveTask(this.editTask).subscribe(
          () => {
            this.taskService.getTaskById(this.editTask.id).subscribe(
              task => {
                this.editTask = task;
              }
            )
          }
        );
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
}
