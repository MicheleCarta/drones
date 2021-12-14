package org.drones.messaging.consumer.iface;

import org.springframework.amqp.core.Message;
import org.springframework.messaging.handler.annotation.Payload;

public interface ConsumerService {
    void flightPathConsumer(@Payload Message message);
}
