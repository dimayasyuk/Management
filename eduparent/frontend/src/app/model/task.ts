import {Status} from "./status";
import {Priority} from "./priority";
import {Account} from "./account";

export class Task {
  id: number;
  code: string;
  projectId: number;
  created: number;
  closed : number;
  updated: number;
  dueDate: number;
  assignee: Account;
  reporter: Account;
  description: string;
  estimation: number;
  status: Status;
  priority: Priority;
}


