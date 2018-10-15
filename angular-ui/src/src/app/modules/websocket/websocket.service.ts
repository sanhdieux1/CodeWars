import {Injectable} from "@angular/core";
import * as Stomp from '@stomp/stompjs';
import * as SockJS from 'sockjs-client';
import {Client} from "@stomp/stompjs";

@Injectable()
export class WebsocketService {
  private client : Client;
  public getMessageClient() {
    if(!this.client){
      this.client = Stomp.over(new SockJS('http://localhost:8080/chatmessage'));
    }
    return this.client;
  }
}
