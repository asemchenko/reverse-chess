package company.controller;

import company.model.chat.GlobalChatMessage;
import company.model.chat.PrivateChatMessage;
import company.repository.GlobalChatMessageRepo;
import company.service.GlobalChatMessageService;
import company.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @Autowired
    private UserService userService;

    @Autowired
    private GlobalChatMessageService globalChatMessageService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public GlobalChatMessage sendMessage(@Payload GlobalChatMessage chatMessage) {
        globalChatMessageService.saveMessage(chatMessage);
        System.out.println(chatMessage.toString());
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public GlobalChatMessage addUser(@Payload GlobalChatMessage chatMessage,
                               SimpMessageHeaderAccessor simpMessageHeaderAccessor){
        userService.addUser(chatMessage.getSender());
        simpMessageHeaderAccessor.getSessionAttributes().put("username", chatMessage.getSender().toString());
        return chatMessage;
    }

    @MessageMapping("/chat.sendToPrivateChat")
    public PrivateChatMessage sendToPrivateChat(@Payload PrivateChatMessage chatMessage,
                                                SimpMessageHeaderAccessor simpMessageHeaderAccessor){
        simpMessagingTemplate.convertAndSendToUser(
                chatMessage.getReceiver().getUsername(), "/receive", chatMessage);
        simpMessagingTemplate.convertAndSendToUser(
                chatMessage.getSender().getUsername(), "/receive", chatMessage);
        System.out.println("privateMessage: " + chatMessage.toString());
        return chatMessage;
    }

}