package websocket.project.websocket.service.observer.salas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import websocket.project.websocket.dto.ResponseMessage;
import websocket.project.websocket.model.User;
import websocket.project.websocket.service.observer.Publisher;
import websocket.project.websocket.service.observer.Subscriber;
import websocket.project.websocket.utils.NotificationUtils;

import java.util.HashSet;
import java.util.Set;

public class Sports implements Publisher {

    public static Set<Subscriber> subscribers = new HashSet<>();
    private static final Logger LOG = LoggerFactory.getLogger(Sports.class);
    private NotificationUtils notificationUtils = new NotificationUtils();


    @Override
    public void notify(String id, String message, SimpMessagingTemplate messagingTemplate) {
        ResponseMessage notificationMessage = new ResponseMessage("Private Notification");
        ResponseMessage msgToSend = new ResponseMessage(message);
        Set<User> usuarios = notificationUtils.getUsersToSendMessage(id);
        usuarios.forEach( u -> {
            //Para o usuario que enviar não ter msg duplicada
            if(!u.getName().equals(id)) {
                messagingTemplate.convertAndSendToUser(u.getName(),
                        "/topic/private-notification", notificationMessage);
                messagingTemplate.convertAndSendToUser(u.getName(),
                        "/topic/private-messages", msgToSend);
            }
        });
    }

    @Override
    public void subscribe(Subscriber subscriber) {
        if(!subscribers.contains(subscriber)) {
            subscribers.add(subscriber);
            if(subscriber instanceof User) {
                LOG.info("Usuario " + ((User) subscriber).getUuid() + " se inscreveu em Sports");
            }else {
                LOG.warn("Subscriber adicionado não é um usuario!!!");
            }
        }else {
            LOG.warn("Subscriber já existe na lista");
        }
    }

    @Override
    public void unsubscribe(Subscriber subscriber) {
        if(subscribers.contains(subscriber)) {
            subscribers.remove(subscriber);
            if(subscriber instanceof User) {
                LOG.info("Usuario " + ((User) subscriber).getUuid() + " se desinscreveu de Sports");
            }else {
                LOG.warn("Subscriber removido não é um usuario!!!");
            }
        }else {
            LOG.warn("Subscriber não existe na lista!!");
        }
    }
}
