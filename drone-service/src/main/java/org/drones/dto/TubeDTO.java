package org.drones.dto;
import com.opencsv.bean.CsvBindByPosition;

import java.io.Serializable;

public class TubeDTO implements Serializable {
    @CsvBindByPosition(position = 0)
    private String stationName;
    @CsvBindByPosition(position = 1)
    private Double latitude;
    @CsvBindByPosition(position = 2)
    private Double longitude;

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
