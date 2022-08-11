package websocket.project.websocket.observer.salas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import websocket.project.websocket.model.User;
import websocket.project.websocket.observer.Publisher;
import websocket.project.websocket.observer.Subscriber;

import java.util.HashSet;
import java.util.Set;

public class Sports implements Publisher {

    public static Set<Subscriber> subscribers = new HashSet<>();
    private static final Logger LOG = LoggerFactory.getLogger(Sports.class);

    @Override
    public void notify(Subscriber subscriber) {
        subscriber.update("Sports");
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
