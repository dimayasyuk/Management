import {Component, Input, OnInit, TemplateRef} from '@angular/core';
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {UserStorageService} from "../../service/user/user-storage.service";
import {TokenStorageService} from "../../service/token/token.storage.service";
import {Router} from "@angular/router";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  public modalRef: BsModalRef;
  @Input()
  subscriptions: Subscription[];

  constructor(private router: Router, private modalService: BsModalService, private tokenStorage: TokenStorageService, private userStorage: UserStorageService) {
  }

  ngOnInit() {

  }

  signOut(): void {
    this.userStorage.signOut();
    this.tokenStorage.signOut();
    this.router.navigate(['/']);
  }

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
  }

  isAdmin(): boolean {
    return this.userStorage.isAdmin();
  }

  isManager(): boolean {
    return this.userStorage.isManager();
  }

  getName(): string{
    return this.userStorage.getAccount().name;
  }

  getSname(): string{
    return this.userStorage.getAccount().sname;
  }

  isTester(): boolean {
    return this.userStorage.isTester();
  }

  isDeveloper(): boolean {
    return this.userStorage.isDeveloper();
  }
}
