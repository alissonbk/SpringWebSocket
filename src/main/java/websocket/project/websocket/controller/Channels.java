package websocket.project.websocket.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import websocket.project.websocket.model.User;
import websocket.project.websocket.observer.Subscriber;
import websocket.project.websocket.observer.salas.News;
import websocket.project.websocket.observer.salas.Sports;
import websocket.project.websocket.observer.salas.Technology;

@RestController
@RequestMapping("channels")
public class Channels {

    private final ApplicationContext context;

    public Channels(ApplicationContext context) {
        this.context = context;
    }


    @GetMapping("/subscribe/technology")
    public Boolean subscribeIntoTechnology() {
        User usuario = context.getBean(User.class);
        usuario.subscribe(new Technology());
        return Technology.subscribers.contains(usuario);
    }

    @GetMapping("/subscribe/news")
    public Boolean subscribeIntoNews() {
        User usuario = context.getBean(User.class);
        usuario.subscribe(new News());
        return News.subscribers.contains(usuario);
    }

    @GetMapping("/subscribe/sports")
    public Boolean subscribeIntoSports() {
        User usuario = context.getBean(User.class);
        usuario.subscribe(new Sports());
        return Sports.subscribers.contains(usuario);
    }

    @GetMapping("/unsubscribe/technology")
    public Boolean unsubscribeIntoTechnology() {
        User usuario = context.getBean(User.class);
        usuario.unsubscribe(new Technology());
        return !Technology.subscribers.contains(usuario);
    }



}
