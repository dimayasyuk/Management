import {Account} from "./account";

export class Comment {
  id: number;
  taskId: number;
  account : Account;
  comment: string;
  posted: number;
}
