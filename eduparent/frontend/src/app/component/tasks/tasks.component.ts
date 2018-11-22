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
        this.loadTasksByProjectId(id);
      }
    )
  }

  loadTasksByProjectId(id: number) {
    this.taskService.getTasksByProjectId(id).subscribe((tasks) => {
      this.tasks = tasks;
      this.loadingService.hide();
    })
  }

  updateTasks(){
    this.loadTasksByProjectId(this.project.id);
  }

  deleteTask(taskId: number):void{
    this.loadingService.show();
    this.taskService.deleteTask(taskId).subscribe(
      () =>{
        this.updateTasks();
      }
    )
  }

  openModal(template: TemplateRef<any>,task: Task) {
    this.editTask = task;
    this.modalRef = this.modalService.show(template);
  }
}
