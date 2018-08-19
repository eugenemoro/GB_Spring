package ru.emorozov.appspringintegration.integration;

import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import ru.emorozov.appspringintegration.dto.TextMessage;

import java.util.logging.Logger;

@MessageEndpoint
public class TextMessageListener {

    private static final Logger LOGGER = Logger.getLogger(TextMessageListener.class.getSimpleName());

    @ServiceActivator(inputChannel = TextMessageGateway.CHANEL)
    public void handler(TextMessage message) {
        LOGGER.info(message.getMessage());
    }

}
