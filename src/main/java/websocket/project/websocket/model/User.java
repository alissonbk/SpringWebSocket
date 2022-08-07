package websocket.project.websocket.model;

import lombok.Getter;
import lombok.Setter;
import websocket.project.websocket.observer.Publisher;
import websocket.project.websocket.observer.Subscriber;
import websocket.project.websocket.observer.salas.Technology;

import java.util.*;

/**
 * Usuario que acessa as salas de chat e é o unico subscriber
 * */
@Getter
@Setter
public class User implements Subscriber {
    /**
     * Lista de salas que esse usuario esta inscrito
     * nesse caso não é static como nas salas {@link Technology}.
     * porque cada instancia de usuario tem uma lista diferente de salas
     * */
    private Set<Publisher> salas = new HashSet<>();
    private UUID uuid = UUID.randomUUID();


    @Override
    public void subscribe(Publisher publisher) {
        this.salas.add(publisher);
        publisher.addSubscriber(this);
    }

    @Override
    public void unsubscribe(Publisher publisher) {
        this.salas.remove(publisher);
        publisher.removeSubscriber(this);
    }

}
