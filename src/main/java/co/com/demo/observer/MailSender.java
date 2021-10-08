package co.com.demo.observer;

import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class MailSender implements Subscriber {

    private static final Logger log = getLogger(MailSender.class);


    @Override
    public void update(String event) {
        log.info("Event triggered {}", event);
    }
}
