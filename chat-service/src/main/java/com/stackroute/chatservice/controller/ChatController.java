package com.stackroute.chatservice.controller;

import com.stackroute.chatservice.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import static java.lang.String.format;

@CrossOrigin
@Controller
public class ChatController {

    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);
    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @MessageMapping("/chat/{roomId}/sendMessage")
    public void sendMessage(@DestinationVariable String roomId, @Payload Message chatMessage) {
        logger.info(roomId+" Chat message recieved is "+chatMessage.getContent());
        messagingTemplate.convertAndSend(format("/chat-room/%s", roomId), chatMessage);
    }

    @MessageMapping("/chat/{roomId}/addUser")
    public void addUser(@DestinationVariable String roomId, @Payload Message chatMessage,
                        SimpMessageHeaderAccessor headerAccessor) {
        String currentRoomId = (String) headerAccessor.getSessionAttributes().put("room_id", roomId);
        if (currentRoomId != null) {
            Message leaveMessage = new Message();
            leaveMessage.setType(Message.MessageType.LEAVE);
            leaveMessage.setSender(chatMessage.getSender());
            messagingTemplate.convertAndSend(format("/chat-room/%s", currentRoomId), leaveMessage);
        }
        headerAccessor.getSessionAttributes().put("name", chatMessage.getSender());
        messagingTemplate.convertAndSend(format("/chat-room/%s", roomId), chatMessage);
    }
    @MessageExceptionHandler
    public String handleException(Throwable exception) {
        messagingTemplate.convertAndSend("/errors", exception.getMessage());
        return exception.getMessage();
    }
}
