import {Component, OnDestroy} from "@angular/core";
import {WebsocketService} from "../websocket/websocket.service";
import {Client, Message, StompSubscription} from "@stomp/stompjs";

@Component({
  selector: "app-chat",
  templateUrl: "./chat.component.html",
  styleUrls : ["./chat.component.sass"]
})
export class ChatComponent implements OnDestroy {

  client: Client;
  subscription: StompSubscription;

  messages: Array<string> = [];

  constructor(private websocketService: WebsocketService){
     this.client = this.websocketService.getMessageClient();
    if(!this.client.connected){
      this.client.connect({}, frame => {
        this.onMessage();
      });
    } else {
      this.onMessage();
    }
  }
  private onMessage(){
    this.subscription = this.client.subscribe("/topic/greetings", (message: Message) => {
      this.messages.push(message.body);
    })
  }

  onSendMessage(message: string){
    this.client.send("/topic/greetings", {}, message)
  }
  ngOnDestroy(): void {
    if(!!this.subscription){
      this.subscription.unsubscribe();
    }
  }
}
