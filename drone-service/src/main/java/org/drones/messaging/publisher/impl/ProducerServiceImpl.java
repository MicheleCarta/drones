package org.drones.messaging.publisher.impl;

import org.drones.dto.JsonSupport;
import org.drones.dto.ReportDTO;
import org.drones.messaging.publisher.iface.ProducerService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ProducerServiceImpl implements ProducerService {
    @Value("${rabbit-queue-reports}")
    private String QUEUE;
    private final RabbitTemplate rabbitTemplate;
    private final JsonSupport jsonSupport;

    @Autowired
    public ProducerServiceImpl(RabbitTemplate rabbitTemplate, JsonSupport jsonSupport) {
        this.rabbitTemplate = rabbitTemplate;
        this.jsonSupport = jsonSupport;
    }

    @Override
    public void pushReport(ReportDTO reportDTO) {
        this.rabbitTemplate.convertAndSend(QUEUE, jsonSupport.convertToJson(reportDTO));
    }
}
