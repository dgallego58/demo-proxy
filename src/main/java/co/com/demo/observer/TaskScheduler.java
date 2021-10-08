package co.com.demo.observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TaskScheduler implements Subscriber {

    private static final Logger log = LoggerFactory.getLogger(TaskScheduler.class);

    @Override
    public void update(String event) {
        log.info("{} triggered event {}", this.getClass().getName(), event);
    }
}
