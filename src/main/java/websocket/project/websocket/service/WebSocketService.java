package websocket.project.websocket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import websocket.project.websocket.dto.Message;
import websocket.project.websocket.dto.ResponseMessage;

import java.sql.Timestamp;
import java.time.Instant;

@Service
public class WebSocketService {
    private final SimpMessagingTemplate messagingTemplate;
    private final NotificationService notificationService;

    public WebSocketService(SimpMessagingTemplate messagingTemplate, NotificationService notificationService) {
        this.messagingTemplate = messagingTemplate;
        this.notificationService = notificationService;
    }

    public void notifyFrontend(final String message){
        ResponseMessage response = new ResponseMessage(message);
        System.out.println(response.getContent());
        notificationService.sendGlobalNotification();
        messagingTemplate.convertAndSend("/topic/messages", response);
    }


    /**
     * Está sendo utilizado o notify dos canais ao inves desse
     * ex: {@link websocket.project.websocket.service.observer.Technology}
     * */
    public void notifyUser(final String id, String message){
        ResponseMessage response = new ResponseMessage(message);
        notificationService.sendPrivateNotification(id, message);
        messagingTemplate.convertAndSendToUser(id,"/topic/private-messages", response);
    }

}
