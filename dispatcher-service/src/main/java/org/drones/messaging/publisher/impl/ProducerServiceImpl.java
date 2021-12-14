package org.drones.messaging.publisher.impl;

import org.drones.dto.FlightPathDTO;
import org.drones.dto.JsonSupport;
import org.drones.messaging.publisher.iface.ProducerService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ProducerServiceImpl implements ProducerService {
    @Value("${rabbit-queue-flight-path}")
    private String QUEUE;
    private final RabbitTemplate rabbitTemplate;
    private final JsonSupport jsonSupport;

    @Autowired
    public ProducerServiceImpl(RabbitTemplate rabbitTemplate, JsonSupport jsonSupport) {
        this.rabbitTemplate = rabbitTemplate;
        this.jsonSupport = jsonSupport;
    }

    @Override
    public void pushNextPath(FlightPathDTO flightPathDTO) {
        this.rabbitTemplate.convertAndSend(QUEUE, jsonSupport.convertToJson(flightPathDTO));
    }
}
