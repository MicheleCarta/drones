package org.drones.business;

import org.drones.dto.Conditions;
import org.drones.dto.FlightPathDTO;
import org.drones.dto.ReportDTO;
import org.drones.dto.TubeDTO;
import org.drones.messaging.publisher.iface.ProducerService;
import org.drones.parser.TubesParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

@Component
public class DroneManager {
    private final DistanceMeterManager distanceMeterManager;
    private final TubesParser parser;
    private final ProducerService producerService;
    @Value("${tube}")
    private String tubes;

    @Autowired
    public DroneManager(DistanceMeterManager distanceMeterManager, TubesParser parser, ProducerService producerService) {
        this.distanceMeterManager = distanceMeterManager;
        this.parser = parser;
        this.producerService = producerService;
    }

    public List<ReportDTO> reportBuilder(FlightPathDTO flightPathDTO) {
        List<ReportDTO> reports = new ArrayList<>();

           StorageManager.getTubes().stream().filter(k -> distanceMeterManager.validatorTubes(flightPathDTO, k.getLatitude(), k.getLongitude()))
                    .forEach(t -> {
                        ReportDTO reportDTO = new ReportDTO();
                        reportDTO.setConditions(generateRandomColor());
                        reportDTO.setTime(flightPathDTO.getTime().doubleValue());
                        reportDTO.setTubeStation(t.getStationName());
                        reportDTO.setDroneId(flightPathDTO.getDroneId());
                        producerService.pushReport(reportDTO);
                        reports.add(reportDTO);
                        //StorageManager.getTubes().remove(t);
                    });
        return reports;
    }

    public void concurrentManager(FlightPathDTO flightPathDTO,Collection<TubeDTO> tubeList) {
        class RemoveItemTask implements Runnable {
            FlightPathDTO dto;
            RemoveItemTask(FlightPathDTO flightPathDTO) { dto = flightPathDTO; }
            public void run() {
                tubeList.stream().
                        filter(k -> distanceMeterManager.validatorTubes(dto,k.getLatitude(),k.getLongitude()))
                        .forEach(t -> {
                            ReportDTO reportDTO = new ReportDTO();
                            reportDTO.setConditions(Conditions.HEAVY);
                            reportDTO.setTime(dto.getTime().doubleValue());
                            reportDTO.setTubeStation(t.getStationName());
                            reportDTO.setDroneId(dto.getDroneId());
                            producerService.pushReport(reportDTO);
                        });
            }
        }
        Thread t = new Thread(new RemoveItemTask(flightPathDTO));
        t.start();
    }

    private Conditions generateRandomColor() {
        Conditions[] values = Conditions.values();
        int length = values.length;
        int randIndex = new Random().nextInt(length);
        return values[randIndex];
    }
}
