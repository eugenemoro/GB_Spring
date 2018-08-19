package ru.emorozov.appspringintegration.integration;

import java.util.logging.Logger;

//@MessageEndpoint
public class SimpleMessageListener {

    private static final Logger LOGGER = Logger.getLogger(SimpleMessageListener.class.getSimpleName());

//    @ServiceActivator(inputChannel = SimpleMessageGateway.CHANEL)
//    public void handler(final SimpleMessage message, @Header("REQ_ID") final String reqId) {
//        LOGGER.info(reqId);
//        LOGGER.info("SimpleMessage: "+message.getDate());
//    }

}
