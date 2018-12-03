import {st} from "@angular/core/src/render3";

export class Status {
  id : number;
  name : string;

   getStatus(status: Status): any{
    if(status.id === this.id && status.name === this.name){
      return this;
    }
  }
}
