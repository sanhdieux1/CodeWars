import { Component } from '@angular/core';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';
import {ServiceTypeDialogComponent} from "./service-type-dialog/service-type-dialog.component";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'my-angular8';
  bsModalRef: BsModalRef;

  constructor(private modalService: BsModalService) {}

  openModalWithComponent() {
    const initialState = {
      title: 'Modal with component'
    };
    this.bsModalRef = this.modalService.show(ServiceTypeDialogComponent, {initialState});
    this.bsModalRef.content.closeBtnName = 'Close';
  }

}
