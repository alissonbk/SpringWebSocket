package websocket.project.websocket.observer;

public interface Publisher {

    void notify(Subscriber subscriber);
    void addSubscriber(Subscriber subscriber);
    void removeSubscriber(Subscriber subscriber);

}
