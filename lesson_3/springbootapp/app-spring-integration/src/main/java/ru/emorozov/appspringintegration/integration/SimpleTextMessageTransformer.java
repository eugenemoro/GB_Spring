package ru.emorozov.appspringintegration.integration;

import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Transformer;
import ru.emorozov.appspringintegration.dto.SimpleMessage;
import ru.emorozov.appspringintegration.dto.TextMessage;

@MessageEndpoint
public class SimpleTextMessageTransformer {

    @Transformer(inputChannel = SimpleMessageGateway.CHANEL, outputChannel = TextMessageGateway.CHANEL)
    public TextMessage transform(SimpleMessage simpleMessage) {
        System.out.println("SimpleTextMessageTransformer");
        return new TextMessage(simpleMessage.getDate().toString());
    }

}
