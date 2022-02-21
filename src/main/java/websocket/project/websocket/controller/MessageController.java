package websocket.project.websocket.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;
import websocket.project.websocket.dto.Message;
import websocket.project.websocket.dto.ResponseMessage;

import java.security.Principal;

@Controller
public class MessageController {

    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public ResponseMessage getMessage(Message message){
        try{
            Thread.sleep(1000);
        } catch(InterruptedException e){
            System.out.println(e);
        }
        return new ResponseMessage(HtmlUtils.htmlEscape(message.getMessageContent()));
    }

    @MessageMapping("/private-message")
    @SendToUser("/topic/private-messages")
    public ResponseMessage getPrivateMessage(final Message message, final Principal principal){
        try{
            Thread.sleep(1000);
        } catch(InterruptedException e){
            System.out.println(e);
        }
        return new ResponseMessage(HtmlUtils
                .htmlEscape("Sending Private Message to user "
                        + principal.getName() + ": " + message.getMessageContent())
        );
    }
}
