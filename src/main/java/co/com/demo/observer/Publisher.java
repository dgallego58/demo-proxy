package co.com.demo.observer;

import java.util.stream.IntStream;

public class Publisher {

    private final EventManager eventManager;

    public Publisher(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    public static void main(String[] args) {
        Subscriber mailSender = new MailSender();
        Subscriber taskScheduler = new TaskScheduler();

        Publisher publisher = new Publisher(new EventManager());
        IntStream.range(0, 100)
                //.parallel()
                .forEach(iteration -> publisher.eventManager.subscribe(new MailSender(), new TaskScheduler()));
        publisher.eventManager.subscribe(mailSender, taskScheduler);
        publisher.eventManager.notify("nueva noticia");
    }

}
