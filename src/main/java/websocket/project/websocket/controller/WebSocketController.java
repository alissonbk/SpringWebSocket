package websocket.project.websocket.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import websocket.project.websocket.dto.Message;
import websocket.project.websocket.service.WebSocketService;

@RestController
public class WebSocketController {
    private final WebSocketService service;

    public WebSocketController(WebSocketService webSocketService) {
        this.service = webSocketService;
    }

    @PostMapping("/send-message")
    public void sendMessage(@RequestBody final Message message){
        service.notifyFrontend(message.getMessageContent());
    }

    @PostMapping("/send-private-message/{id}")
    public void sendMessage(@PathVariable final String id, @RequestBody final Message message){
        service.notifyUser(id, message.getMessageContent());
    }

}

