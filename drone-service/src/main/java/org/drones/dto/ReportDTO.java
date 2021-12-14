package org.drones.dto;

import java.io.Serializable;

public class ReportDTO implements Serializable {
    private Integer droneId;
    private Double time;
    private String TubeStation;
    private Conditions conditions;

    public Integer getDroneId() {
        return droneId;
    }

    public void setDroneId(Integer droneId) {
        this.droneId = droneId;
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    public String getTubeStation() {
        return TubeStation;
    }

    public void setTubeStation(String tubeStation) {
        TubeStation = tubeStation;
    }

    public Conditions getConditions() {
        return conditions;
    }

    public void setConditions(Conditions conditions) {
        this.conditions = conditions;
    }
}

