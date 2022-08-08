package websocket.project.websocket.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import websocket.project.websocket.observer.Publisher;
import websocket.project.websocket.observer.Subscriber;
import websocket.project.websocket.observer.salas.Technology;

import javax.security.auth.Subject;
import java.nio.file.attribute.UserPrincipal;
import java.util.*;

/**
 * Usuario que acessa as salas de chat e é o unico subscriber;
 * Porque @Component -> como não tem implementação de security o usuario precisa ser definido
 * como uma Bean para poder saber qual usuario está fazendo a requisição no contexto
 * {@link websocket.project.websocket.controller.Channels }
 * */
@Getter
@Component
public class User implements Subscriber{
    /**
     * Lista de salas que esse usuario esta inscrito
     * nesse caso não é static como nas salas {@link Technology}.
     * porque cada instancia de usuario tem uma lista diferente de salas
     * */
    @SuppressWarnings("FieldMayBeFinal")
    private Set<Publisher> salas = new HashSet<>();
    private final UUID uuid = UUID.randomUUID();

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
