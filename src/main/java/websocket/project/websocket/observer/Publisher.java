package websocket.project.websocket.observer;

public interface Publisher {

    void notify(Subscriber subscriber);
    void subscribe(Subscriber subscriber);
    void unsubscribe(Subscriber subscriber);

}
