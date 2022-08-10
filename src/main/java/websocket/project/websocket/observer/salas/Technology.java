package websocket.project.websocket.observer.salas;

import lombok.Getter;
import websocket.project.websocket.model.User;
import websocket.project.websocket.observer.Publisher;
import websocket.project.websocket.observer.Subscriber;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

@Getter
public class Technology implements Publisher {
    /**
     * Lista dos inscritos nessa sala
     * static pq todos que instanciam Tecnologia devem acessar a mesma lista
     * */
    @SuppressWarnings("FieldMayBeFinal")
    public static Set<Subscriber> subscribers = new HashSet<>();

    @Override
    public void notify(Subscriber subscriber) {
        //TODO
        System.out.println(subscribers);
    }

    @Override
    public void addSubscriber(Subscriber subscriber) {
        if(subscriber instanceof User) {
            System.out.println("Usuario "
                    + ((User) subscriber).getUuid() + " se inscreveu em Technology");
        }
        subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(Subscriber subscriber) {
        if(subscriber instanceof User) {
            System.out.println("Usuario "
                    + ((User) subscriber).getUuid() + " se desinscreveu de Technology");
        }
        subscribers.remove(subscriber);
    }
}
