package websocket.project.websocket.model;

import lombok.Getter;
import websocket.project.websocket.service.observer.Subscriber;

import javax.security.auth.Subject;
import java.security.Principal;
import java.util.*;

/**
 * Usuario que acessa as salas de chat e é o unico subscriber;
 * precisa implementar Principal por causa do UserHanshakeHandler
 * {@link websocket.project.websocket.handler.UserHandshakeHandler}
 * */
@Getter
public class User implements Subscriber, Principal {
    private String lastPublisher;
    private UUID uuid = UUID.randomUUID();

    @Override
    public void update(String lastPublisher) {
        this.lastPublisher = lastPublisher;
    }

    @Override
    public String getName() {
        return this.getUuid().toString();
    }

    @Override
    public boolean implies(Subject subject) {
        return Principal.super.implies(subject);
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    /**
     * Implementado equals and hashcode para poder utilizar o contains da forma desejada (comparando o uuid)
     * metodo contains do java utiliza equals na impl... {@link Set#contains(Object)}
     * */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(uuid, user.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
}
