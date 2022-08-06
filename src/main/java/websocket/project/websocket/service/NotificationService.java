package websocket.project.websocket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import websocket.project.websocket.dto.ResponseMessage;

import java.time.Instant;

@Service
public class NotificationService {
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public NotificationService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendGlobalNotification(){
        ResponseMessage message = new ResponseMessage("Global Notification");
        messagingTemplate.convertAndSend("/topic/global-notification", message);
    }

    public void sendPrivateNotification(final String id){
        ResponseMessage message = new ResponseMessage("Global Notification");
        messagingTemplate.convertAndSendToUser(id, "/topic/private-notification", message);
    }


}
