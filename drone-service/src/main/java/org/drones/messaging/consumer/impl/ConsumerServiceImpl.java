package org.drones.messaging.consumer.impl;

import org.drones.business.FlightManager;
import org.drones.dto.FlightPathDTO;
import org.drones.dto.JsonSupport;
import org.drones.messaging.consumer.iface.ConsumerService;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConsumerServiceImpl implements ConsumerService {
    private final JsonSupport jsonSupport;
    private final FlightManager flightManager;
    private final RabbitTemplate rabbitTemplate;
    @Value("${rabbit-queue-flight-path}")
    private String queue;

    @Autowired
    public ConsumerServiceImpl(JsonSupport jsonSupport, FlightManager flightManager, RabbitTemplate rabbitTemplate) {
        this.jsonSupport = jsonSupport;
        this.flightManager = flightManager;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    @RabbitListener(id = "flightPath",
            bindings = @QueueBinding(
                    value = @Queue(value = "${rabbit-queue-flight-path}", durable = "true", autoDelete = "false", declare = "${rabbit-queue-flight-path}"),
                    exchange = @Exchange(value = "${rabbit-exchange}", durable = "true", type = ExchangeTypes.TOPIC)),
            exclusive = true)
    public void flightPathConsumer(Message message) {
        String bodyMessage = new String(message.getBody());
        FlightPathDTO flightPathDTO = new FlightPathDTO();
        flightPathDTO = (FlightPathDTO) jsonSupport.convertToObject(bodyMessage, flightPathDTO);
        flightManager.reportBuilder(flightPathDTO);

    }
}
