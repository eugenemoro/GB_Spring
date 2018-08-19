package ru.emorozov.appspringintegration.integration;

import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import ru.emorozov.appspringintegration.dto.SimpleMessage;

import java.util.logging.Logger;

@MessageEndpoint
public class TestListener {

    private static final Logger LOGGER = Logger.getLogger(TestListener.class.getSimpleName());

    @ServiceActivator(inputChannel = "testChanel")
    public void handler(final SimpleMessage message) {
        LOGGER.info("TestListener: "+message.getDate());
    }

}
