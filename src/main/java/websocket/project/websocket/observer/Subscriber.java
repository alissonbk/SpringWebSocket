package websocket.project.websocket.observer;

public interface Subscriber {

    void subscribe(Publisher publisher);

    void unsubscribe(Publisher publisher);

}
