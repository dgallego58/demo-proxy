package co.com.demo.observer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class EventManager {

    private final Set<Subscriber> subscribers;

    public EventManager() {
        this.subscribers = new HashSet<>();
    }

    public void subscribe(Subscriber subscriber) {
        this.subscribers.add(subscriber);
    }

    public void subscribe(Subscriber... subscribers) {
        var subs = Arrays.stream(subscribers).collect(Collectors.toSet());
        this.subscribers.addAll(subs);
    }

    public void unSubscribe(Subscriber subscriber) {
        this.subscribers.remove(subscriber);
    }

    public void notify(String event) {
        subscribers.forEach(subscriber -> subscriber.update(event));
    }

}
