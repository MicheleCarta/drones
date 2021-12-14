package org.drones.business;

import org.drones.dto.TubeDTO;
import org.drones.parser.TubesParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
@Component
public class Startup {
    private final TubesParser parser;
    @Value("${tube}")
    private String tubes;
    @Autowired
    public Startup(TubesParser parser) {
        this.parser = parser;
    }

    @PostConstruct
    public void fetchTubeStation(){
        List<TubeDTO> tube = parser.parse(new TubeDTO(),tubes);
        tube.stream().forEach( k-> {
            StorageManager.getTubes().add(k);
        });
    }
}
