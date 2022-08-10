package websocket.project.websocket.observer.salas;

import websocket.project.websocket.model.User;
import websocket.project.websocket.observer.Publisher;
import websocket.project.websocket.observer.Subscriber;

import java.util.HashSet;
import java.util.Set;

public class Sports implements Publisher {

    public static Set<Subscriber> subscribers = new HashSet<>();

    @Override
    public void notify(Subscriber subscriber) {
        //TODO
    }

    @Override
    public void addSubscriber(Subscriber subscriber) {
        if(subscriber instanceof User) {
            System.out.println("Usuario "
                    + ((User) subscriber).getUuid() + " se inscreveu em Sports");
        }
        subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(Subscriber subscriber) {
        if(subscriber instanceof User) {
            System.out.println("Usuario "
                    + ((User) subscriber).getUuid() + " se desinscreveu de Sports");
        }
        subscribers.remove(subscriber);
    }
}
