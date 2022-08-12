package websocket.project.websocket.utils;

import websocket.project.websocket.model.User;
import websocket.project.websocket.service.observer.News;
import websocket.project.websocket.service.observer.Publisher;
import websocket.project.websocket.service.observer.Sports;
import websocket.project.websocket.service.observer.Technology;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class NotificationUtils {

    /**
     * Pega todos os canais ou salas em que o usuario está inscrito
     * e pega todos os usuarios dessas reespectivas salas
     * TODO -> refatorar essa gamb e receber o usuario invés do uuid
     * */
    public Set<User> getUsersToSendMessage(final String id) {
        Set<User> usersIds = new HashSet<>();
        User user = new User();
        user.setUuid(UUID.fromString(id));
        if (Technology.subscribers.contains(user)) {
            Technology.subscribers.forEach( s -> {
                if(s instanceof User) {
                    usersIds.add((User) s);
                }
            });
        }
        if (News.subscribers.contains(user)) {
            News.subscribers.forEach( s -> {
                if( s instanceof User) {
                    usersIds.add((User) s);
                }
            });
        }
        if (Sports.subscribers.contains(user)) {
            Sports.subscribers.forEach( s -> {
                if( s instanceof User) {
                    usersIds.add((User) s);
                }
            });
        }
        return usersIds;
    }

    /**
     * Utilizado para notificação
     * */
    public Set<Publisher> getChannelsToNotify(String id) {
        Set<Publisher> publishers = new HashSet<>();
        Technology.subscribers.forEach(s -> {
            if(s instanceof User && ((User) s).getName().equals(id)) {
                publishers.add(new Technology());
            }
        });
        News.subscribers.forEach(s -> {
            if(s instanceof User && ((User) s).getName().equals(id)) {
                publishers.add(new News());
            }
        });
        Sports.subscribers.forEach(s -> {
            if(s instanceof User && ((User) s).getName().equals(id)) {
                publishers.add(new Sports());
            }
        });
        return publishers;
    }

}
