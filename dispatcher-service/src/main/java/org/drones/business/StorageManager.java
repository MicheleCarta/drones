package org.drones.business;

import org.drones.dto.FlightPathDTO;

import java.util.concurrent.ConcurrentHashMap;

public class StorageManager {
    private static ConcurrentHashMap<Integer, FlightPathDTO> droneA = new ConcurrentHashMap<Integer, FlightPathDTO>();
    private static ConcurrentHashMap<Integer, FlightPathDTO> droneB = new ConcurrentHashMap<Integer, FlightPathDTO>();

    public static ConcurrentHashMap<Integer, FlightPathDTO> getDroneA() {
        return droneA;
    }

    public static void setDroneA(ConcurrentHashMap<Integer, FlightPathDTO> droneA) {
        StorageManager.droneA = droneA;
    }

    public static ConcurrentHashMap<Integer, FlightPathDTO> getDroneB() {
        return droneB;
    }

    public static void setDroneB(ConcurrentHashMap<Integer, FlightPathDTO> droneB) {
        StorageManager.droneB = droneB;
    }
}
