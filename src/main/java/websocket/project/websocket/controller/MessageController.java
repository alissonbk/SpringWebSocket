package websocket.project.websocket.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;
import websocket.project.websocket.dto.Message;
import websocket.project.websocket.dto.ResponseMessage;
import websocket.project.websocket.service.NotificationService;

import java.security.Principal;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

/**
 * Controller responsavel por receber as mensagens
 * */
@RestController
public class MessageController {

    private final NotificationService notificationService;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public MessageController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public ResponseMessage getMessage(Message message){
        try{
            Thread.sleep(message.getDelay());
        } catch(InterruptedException e){
            System.out.println(e);
        }
        notificationService.sendGlobalNotification();
        message.setDate(Instant.now());
        return new ResponseMessage(
                HtmlUtils.htmlEscape(sdf.format(Date.from(message.getDate())) + ": " + message.getMessageContent()));
    }

    @MessageMapping("/private-message")
    @SendToUser("/topic/private-messages")
    public ResponseMessage getPrivateMessages(final Message message, final Principal principal){
        message.setDate(Instant.now());
        String msgToSend = sdf.format(Date.from(message.getDate())) + ": Enviando mensagem privada para o usuario:  "
                + principal.getName() + ": " + message.getMessageContent();
        //DELAY
        try{
            Thread.sleep(message.getDelay());
        } catch(InterruptedException e){
            System.out.println(e);
        }
        notificationService.sendPrivateNotification(principal.getName(), msgToSend);

        return new ResponseMessage(HtmlUtils.htmlEscape(msgToSend));
    }
}
