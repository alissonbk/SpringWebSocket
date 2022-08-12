package websocket.project.websocket.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import websocket.project.websocket.model.User;
import websocket.project.websocket.service.observer.News;
import websocket.project.websocket.service.observer.Sports;
import websocket.project.websocket.service.observer.Technology;

import java.util.UUID;

@RestController
@RequestMapping("channels")
public class ChannelsController {
    private final ApplicationContext context;
    private final Sports sports;
    private final Technology technology;
    private final News news;

    public ChannelsController(ApplicationContext context, Sports sports, Technology technology, News news) {
        this.context = context;
        this.sports = sports;
        this.technology = technology;
        this.news = news;
    }

    @GetMapping("/subscribe/technology/{uuid}")
    public Boolean subscribeIntoTechnology(@PathVariable String uuid) {
        User usuario = new User();
        usuario.setUuid(UUID.fromString(uuid));

        this.technology.subscribe(usuario);
        return Technology.subscribers.contains(usuario);
    }

    @GetMapping("/subscribe/news/{uuid}")
    public Boolean subscribeIntoNews(@PathVariable String uuid) {
        User usuario = new User();
        usuario.setUuid(UUID.fromString(uuid));

        this.news.subscribe(usuario);
        return News.subscribers.contains(usuario);
    }

    @GetMapping("/subscribe/sports/{uuid}")
    public Boolean subscribeIntoSports(@PathVariable String uuid) {
        User usuario = new User();
        usuario.setUuid(UUID.fromString(uuid));

        this.sports.subscribe(usuario);
        return Sports.subscribers.contains(usuario);
    }

    @GetMapping("/unsubscribe/technology/{uuid}")
    public Boolean unsubscribeFromTechnology(@PathVariable String uuid) {
        User usuario = new User();
        usuario.setUuid(UUID.fromString(uuid));

        this.technology.unsubscribe(usuario);
        return !Technology.subscribers.contains(usuario);
    }

    @GetMapping("/unsubscribe/sports/{uuid}")
    public Boolean unsubscribeFromSports(@PathVariable String uuid) {
        User usuario = new User();
        usuario.setUuid(UUID.fromString(uuid));

        this.sports.unsubscribe(usuario);
        return !Sports.subscribers.contains(usuario);
    }

    @GetMapping("/unsubscribe/news/{uuid}")
    public Boolean unsubscribeFromNews(@PathVariable String uuid) {
        User usuario = new User();
        usuario.setUuid(UUID.fromString(uuid));

        this.news.unsubscribe(usuario);
        return !News.subscribers.contains(usuario);
    }



}
