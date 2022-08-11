package websocket.project.websocket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import websocket.project.websocket.dto.ResponseMessage;
import websocket.project.websocket.model.User;
import websocket.project.websocket.observer.Publisher;
import websocket.project.websocket.observer.Subscriber;
import websocket.project.websocket.observer.salas.News;
import websocket.project.websocket.observer.salas.Sports;
import websocket.project.websocket.observer.salas.Technology;

import java.time.Instant;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

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

    /**
     * Envia mensagem privada apenas para os canais que o usuario está inscrito/todos usuarios inscritos nestes canais
     * */
    public void sendPrivateNotification(final String id){
        ResponseMessage message = new ResponseMessage("Global Notification");
        Set<User> usuarios = this.getUsersToSendMessage(id);
        usuarios.forEach( u -> {
            messagingTemplate.convertAndSendToUser(u.getUuid().toString(),
                    "/topic/private-notification", message);
        });

    }


    /**
     * Pega todos os canais ou salas em que o usuario está inscrito
     * e pega todos os usuarios dessas reespectivas salas
     * TODO -> refatorar essa gamb e receber o usuario invés do uuid
     * */
    public Set<User> getUsersToSendMessage(final String id) {
        Set<User> usersIds = new HashSet<>();
        User user = new User();
        user.setUuid(UUID.fromString(id));
        if (Technology.subscribers.contains(user)) {
            Technology.subscribers.forEach( s -> {
                if(s instanceof User) {
                    usersIds.add((User) s);
                }
            });
        }
        if (News.subscribers.contains(user)) {
            News.subscribers.forEach( s -> {
                if( s instanceof User) {
                    usersIds.add((User) s);
                }
            });
        }
        if (Sports.subscribers.contains(user)) {
            Sports.subscribers.forEach( s -> {
                if( s instanceof User) {
                    usersIds.add((User) s);
                }
            });
        }
        return usersIds;
    }


}
