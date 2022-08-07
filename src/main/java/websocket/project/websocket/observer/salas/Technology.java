package websocket.project.websocket.observer.salas;

import websocket.project.websocket.observer.Publisher;
import websocket.project.websocket.observer.Subscriber;

import java.util.HashSet;
import java.util.Set;

public class Technology implements Publisher {
    /**
     * Lista dos inscritos nessa sala
     * static pq todos que instanciam Tecnologia devem acessar a mesma lista
     * */
    private static Set<Subscriber> subscribers = new HashSet<>();

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
