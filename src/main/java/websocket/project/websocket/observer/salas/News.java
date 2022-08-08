package websocket.project.websocket.observer.salas;

import websocket.project.websocket.observer.Publisher;
import websocket.project.websocket.observer.Subscriber;

import java.util.HashSet;
import java.util.Set;

public class News implements Publisher {

    public static Set<Subscriber> subscribers = new HashSet<>();

    @Override
    public void notify(Subscriber subscriber) {
        //TODO
    }

    @Override
    public void addSubscriber(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }
}
