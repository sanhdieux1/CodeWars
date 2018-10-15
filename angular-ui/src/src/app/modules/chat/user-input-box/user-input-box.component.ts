import {Component, EventEmitter, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-user-input-box',
  templateUrl: './user-input-box.component.html',
  styleUrls: ['./user-input-box.component.scss']
})
export class UserInputBoxComponent implements OnInit {

  @Output("onSendMessage")
  event: EventEmitter<string> = new EventEmitter();

  message: string = '';

  constructor() {
  }

  ngOnInit() {
  }

  sendMessage() {
    if (!!this.message) {
      this.event.next(this.message);
      this.message = '';
    }
  }
}
