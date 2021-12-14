package org.drones.business;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.drones.dto.FlightPathDTO;
import org.drones.messaging.publisher.iface.ProducerService;
import org.drones.parser.FlightPathParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Component
public class DispatcherManager {
    private static final Logger logger = LogManager.getLogger(DispatcherManager.class);
    private final FlightPathParser parser;
    private final ProducerService producerService;
    @Value("${6043}")
    private String drone1;
    @Value("${5937}")
    private String drone2;

    @Autowired
    public DispatcherManager(FlightPathParser parser, ProducerService producerService) {
        this.parser = parser;
        this.producerService = producerService;
    }

    public void fetchDrones() {
        fetchDrone1();
        fetchDrone2();
    }

    private void fetchDrone1() {
        List<FlightPathDTO> paths = parser.parse(new FlightPathDTO(), drone1);
        paths.stream().forEach(k -> {
            StorageManager.getDroneA().putIfAbsent(k.getTime(), k);
        });
    }

    private void fetchDrone2() {
        List<FlightPathDTO> paths = parser.parse(new FlightPathDTO(), drone2);
        paths.stream().forEach(k -> {
            StorageManager.getDroneB().putIfAbsent(k.getTime(), k);
        });
    }

    public void pushScheduler() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            Integer currentTimer = 1;

            @Override
            public void run() {
                if (currentTimer == 1520) {
                    timer.cancel();
                    logger.info("Simulation Completed");
                }
                logger.info("current time is " + currentTimer);
                if (StorageManager.getDroneB().containsKey(currentTimer) == true) {
                    producerService.pushNextPath(StorageManager.getDroneB().get(currentTimer));
                    StorageManager.getDroneB().remove(currentTimer);
                }
                if (StorageManager.getDroneA().containsKey(currentTimer) == true) {
                    producerService.pushNextPath(StorageManager.getDroneA().get(currentTimer));
                    StorageManager.getDroneA().remove(currentTimer);
                }
                currentTimer++;


            }
        }, 1000, 1000);

    }

}
