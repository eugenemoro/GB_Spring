package ru.emorozov.appspringintegration.integration;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;
import ru.emorozov.appspringintegration.dto.SimpleMessage;

@MessagingGateway(name = SimpleMessageGateway.GATEWAY, defaultRequestChannel = SimpleMessageGateway.CHANEL)
public interface SimpleMessageGateway {

    String CHANEL = "simpleMessageChannel";

    String GATEWAY = "simpleMessageGateway";

    @Gateway
    void fire(Message<SimpleMessage> message);

}
