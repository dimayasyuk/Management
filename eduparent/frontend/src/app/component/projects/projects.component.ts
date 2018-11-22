import {Component, OnInit} from '@angular/core';
import {TaskService} from "../../service/task/task.service";
import {ProjectService} from "../../service/project/project.service";
import {ActivatedRoute} from "@angular/router";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {BsModalService} from "ngx-bootstrap";
import {Task} from "../../model/task";
import {FileService} from "../../service/file/file.service";

@Component({
  selector: 'app-projects',
  templateUrl: './projects.component.html',
  styleUrls: ['./projects.component.css']
})
export class ProjectsComponent implements OnInit {

  public editTask: Task;
  public files: any = File;

  constructor(private taskService: TaskService, private projectService: ProjectService,
              private route: ActivatedRoute, private loadingService: Ng4LoadingSpinnerService, private modalService: BsModalService,private fileService:FileService) {
  }

  ngOnInit() {
    const id = +this.route.snapshot.paramMap.get('id');
    this.loadTaskById(id);
  }

  loadTaskById(id: number) {
    this.loadingService.show();
    this.taskService.getTaskById(id).subscribe(
      (task) => {
        this.editTask = task;
        console.log(this.editTask);
      }
    )
  }

  selectFile(event){
   const file = event.target.files[0];
   this.files = file;
   console.log(file);
  }

  upload(): void{
    const formData = new FormData();
    formData.append('file',this.files);
    this.fileService.saveFile(formData);
  }
}
