package org.drones.messaging.publisher.iface;

import org.drones.dto.FlightPathDTO;

public interface ProducerService {
    void pushNextPath(FlightPathDTO flightPathDTO);
}
