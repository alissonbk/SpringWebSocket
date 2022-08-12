package websocket.project.websocket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import websocket.project.websocket.dto.ResponseMessage;
import websocket.project.websocket.model.User;
import websocket.project.websocket.service.observer.Publisher;
import websocket.project.websocket.service.observer.salas.News;
import websocket.project.websocket.service.observer.salas.Sports;
import websocket.project.websocket.service.observer.salas.Technology;
import websocket.project.websocket.utils.NotificationUtils;

import java.util.HashSet;
import java.util.Set;

@Service
public class NotificationService {
    private final SimpMessagingTemplate messagingTemplate;
    private NotificationUtils notificationUtils = new NotificationUtils();

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
    public void sendPrivateNotification(final String id, final String msg){
        Set<Publisher> channelsToNotify = this.getChannelsToNotify(id);
        if(channelsToNotify.size() > 0) {
            channelsToNotify.forEach( c -> c.notify(id, msg, messagingTemplate));
        }

    }

    private Set<Publisher> getChannelsToNotify(String id) {
        Set<Publisher> publishers = new HashSet<>();
        Technology.subscribers.forEach(s -> {
            if(s instanceof User && ((User) s).getName().equals(id)) {
                publishers.add(new Technology());
            }
        });
        News.subscribers.forEach(s -> {
            if(s instanceof User && ((User) s).getName().equals(id)) {
                publishers.add(new News());
            }
        });
        Sports.subscribers.forEach(s -> {
            if(s instanceof User && ((User) s).getName().equals(id)) {
                publishers.add(new Sports());
            }
        });
        return publishers;
    }


}
