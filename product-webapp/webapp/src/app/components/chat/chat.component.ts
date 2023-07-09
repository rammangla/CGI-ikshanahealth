import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder,FormControl,FormGroup,Validators } from '@angular/forms';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
/*import { MatDialog } from '@angular/material/dialog';*/
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit, OnDestroy {

  webSocketEndPoint:string ='https://ikshanahealth.stackroute.io/chat-service/sock';
  chat:FormGroup;
  name:string;
  room:any;
  message:string;
  showchat:boolean=false;
  stompClient: any;

  constructor(private fb:FormBuilder,private route: ActivatedRoute) { }
  ngOnInit(): void {
    this.name=this.route.snapshot.params["name"];
    this.room=this.route.snapshot.params["room"];
    this.chat=this.fb.group({
      // name: new FormControl('',[Validators.required]),
      // room: new FormControl('',[Validators.required]),
      message: new FormControl(''),
    })
  } 

  ngOnDestroy(): void {
    this.stompClient.unsubscribe();
  }
  
  connect(): void{

    const socket = new SockJS(this.webSocketEndPoint);
    this.name=this.route.snapshot.params["name"];
    this.room=this.route.snapshot.params["room"];
    console.log(this.name);
    console.log(this.room);
    this.stompClient = Stomp.over(socket);
    this.stompClient.connect({}, (frame: string) => {
      this.showchat=true;
      this.stompClient.subscribe(`/chat-room/`+this.room, this.onMessageReceived);
      this.stompClient.send(`/chat-app/chat/`+this.room+`/addUser`,
      {},
      JSON.stringify({sender: this.name, type: 'JOIN'})
    );
      
    }

      ,{}
      );
  }
  sendMessage() : void{
    this.message = this.chat.get('message').value;
    console.log(this.name);
    console.log(this.message)
    if(this.message && this.stompClient) {
        var chatMessage = {
            sender: this.name,
            content: this.message,
            type: 'CHAT'
        };
        
        console.log(chatMessage)
        this.room=this.route.snapshot.params["room"];

        console.log(this.room);
        
        this.stompClient.send(`/chat-app/chat/`+this.room+`/sendMessage`, {}, JSON.stringify(chatMessage));
        // document.querySelector('message').textContent = '';
        this.chat.reset({
          message: ''
        });
    }
    //event.preventDefault();
  }







  onMessageReceived(payload:any):void {
    const message = JSON.parse(payload.body);
    const messageElement = document.createElement('li');
    const divCard = document.createElement('div');
    divCard.className = 'card';
  
    if(message.type === 'JOIN') {
        messageElement.classList.add('event-message');
        message.content = message.sender + ' joined!';
    } else if (message.type === 'LEAVE') {
        messageElement.classList.add('event-message');
        message.content = message.sender + ' left!';
    } else {
        messageElement.classList.add('chat-message');
  
        const avatarElement = document.createElement('i');
        const avatarText = document.createTextNode(message.sender[0]);
        avatarElement.appendChild(avatarText);

      //   var colors:string[]; 
      //   this.colors = [
      //     '#2196F3', '#32c787', '#00BCD4', '#ff5652',
      //     '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
      // ];
      //   var hash = 0;

    
        // var index = Math.abs(hash % this.colors.length);

        const colors = ['#2196F3', '#32c787', '#00BCD4', '#ff5652', '#ffc107', '#ff85af', '#FF9800', '#39bbb0']

        var hash=0;
        for (var i = 0; i < message.sender.length; i++) {
          hash = 31 * hash + message.sender.charCodeAt(i);
      }
          
        const index = Math.abs(hash % colors.length)
        const color=colors[index]
        // const color = colors[Math.floor(Math.random() * colors.length)]
        console.log(colors);
        console.log(color);
        
        avatarElement.style['background-color'] =  color;
        avatarElement.style['position'] =  'absolute';
        avatarElement.style['width'] =  '42px';
        avatarElement.style['height'] =  '42px';
        avatarElement.style['display'] =  'inline-block';
        avatarElement.style['border-radius'] =  '50%';
        avatarElement.style['color'] =  '#fff';
        avatarElement.style['font-size'] =  '18px';
        avatarElement.style['text-transform'] =  'uppercase';
        avatarElement.style['overflow'] =  'hidden';
        avatarElement.style['left'] =  '10px';
        avatarElement.style['line-height'] =  '42px';
        avatarElement.style['vertical-align'] =  'middle';
        avatarElement.style['textAlign']='center';



        // vertical-align: middle;
        // line-height: 42px;
        // font-style: normal;
        messageElement.style['padding-left']='68px';
        messageElement.style['position']='relative';
        messageElement.style['max-width']='600px';

      



        messageElement.appendChild(avatarElement);
  
        const usernameElement = document.createElement('span');
        const usernameText = document.createTextNode(message.sender);
        usernameElement.appendChild(usernameText);
        usernameElement.style['font-weight']='bold';
        messageElement.appendChild(usernameElement);
  
  
  
        const divCardBody = document.createElement('div');
        divCardBody.className = 'card-body';
  
        divCardBody.appendChild(messageElement);
        divCard.appendChild(divCardBody);
    }
  
    const textElement = document.createElement('p');
    const messageText = document.createTextNode(message.content);
    textElement.appendChild(messageText);
  
    messageElement.appendChild(textElement);
    const messageArea = document.querySelector('#messageArea');
    messageArea.appendChild(divCard);
    messageArea.scrollTop = messageArea.scrollHeight;
  }
  
}


