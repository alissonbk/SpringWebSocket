package websocket.project.websocket.observer;

import org.springframework.messaging.simp.SimpMessagingTemplate;

public interface Publisher {

    void notify(String id, String message, SimpMessagingTemplate messagingTemplate);
    void subscribe(Subscriber subscriber);
    void unsubscribe(Subscriber subscriber);

}
