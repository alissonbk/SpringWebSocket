package websocket.project.websocket.controller;

import com.sun.net.httpserver.Headers;
import org.springframework.context.ApplicationContext;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import websocket.project.websocket.configuration.WebSocketConfig;
import websocket.project.websocket.handler.UserHandshakeHandler;
import websocket.project.websocket.model.User;
import websocket.project.websocket.observer.salas.News;
import websocket.project.websocket.observer.salas.Sports;
import websocket.project.websocket.observer.salas.Technology;

import java.util.UUID;

@RestController
@RequestMapping("channels")
public class Channels {
    private final ApplicationContext context;

    public Channels(ApplicationContext context) {
        this.context = context;
    }

    @GetMapping("/subscribe/technology/{uuid}")
    public Boolean subscribeIntoTechnology(@PathVariable String uuid) {
        User usuario = new User();
        usuario.setUuid(UUID.fromString(uuid));
        usuario.subscribe(new Technology());
        return Technology.subscribers.contains(usuario);
    }

    @GetMapping("/subscribe/news/{uuid}")
    public Boolean subscribeIntoNews(@PathVariable String uuid) {
        User usuario = new User();
        usuario.setUuid(UUID.fromString(uuid));
        usuario.subscribe(new News());
        return News.subscribers.contains(usuario);
    }

    @GetMapping("/subscribe/sports/{uuid}")
    public Boolean subscribeIntoSports(@PathVariable String uuid) {
        User usuario = new User();
        usuario.setUuid(UUID.fromString(uuid));
        usuario.subscribe(new Sports());
        return Sports.subscribers.contains(usuario);
    }

    @GetMapping("/unsubscribe/technology")
    public Boolean unsubscribeFromTechnology() {
        User usuario = context.getBean(UserHandshakeHandler.class).user;
        usuario.unsubscribe(new Technology());
        return !Technology.subscribers.contains(usuario);
    }

    @GetMapping("/unsubscribe/sports")
    public Boolean unsubscribeFromSports() {
        User usuario = context.getBean(UserHandshakeHandler.class).user;
        usuario.unsubscribe(new Sports());
        return !Sports.subscribers.contains(usuario);
    }

    @GetMapping("/unsubscribe/news")
    public Boolean unsubscribeFromNews() {
        User usuario = context.getBean(UserHandshakeHandler.class).user;
        usuario.unsubscribe(new News());
        return !News.subscribers.contains(usuario);
    }



}
