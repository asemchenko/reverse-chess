package company.configuration;

import company.model.User;
import company.model.chat.GlobalChatMessage;
import company.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketEventListener {

    @Autowired
    private SimpMessageSendingOperations messageSendingOperations;

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        logger.info("Received a new web socket connection");
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        User userLeaver = userService.returnUserByUserName(
                (String) headerAccessor.getSessionAttributes().get("username"));
        if (userLeaver != null) {
            logger.info("User Disconnected : " + userLeaver.getUsername());
            GlobalChatMessage globalChatMessage = new GlobalChatMessage();
            globalChatMessage.setMessageType(GlobalChatMessage.MessageType.LEAVE);
            globalChatMessage.setSender(userLeaver);
            messageSendingOperations.convertAndSend("/topic/public", globalChatMessage);
        }
    }

}
