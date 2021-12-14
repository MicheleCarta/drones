package org.drones.business;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.drones.dto.FlightPathDTO;
import org.springframework.stereotype.Component;

@Component
public class DistanceMeter {
    private static final Logger logger = LogManager.getLogger(DistanceMeter.class);

    public boolean validatorTubes(FlightPathDTO flightPathDTO, final double lat, final double lng) {
        boolean res = false;
        final GeometryFactory geometryFactory = new GeometryFactory();
        final Coordinate target = new Coordinate(lat, lng);
        final Coordinate coordinate = new Coordinate(
                flightPathDTO.getLatitude(), flightPathDTO.getLongitude());
        final Geometry point = geometryFactory.createPoint(coordinate);
        if (point.getCoordinate().distance(target) <= 0.035) {
            logger.info("your distance is " + point.getCoordinate().distance(target));
            res = true;
        }
        return res;
    }
}
