package org.drones.messaging.publisher.iface;

import org.drones.dto.ReportDTO;

public interface ProducerService {
    void pushReport(ReportDTO reportDTO);
}
