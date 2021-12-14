package org.drones.parser;

import org.drones.dto.TubeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ParserManager {
    private final TubesParser tubesParser;
    @Value("${tube}")
    private String tube;
    @Autowired
    public ParserManager(TubesParser tubesParser) {
        this.tubesParser = tubesParser;
    }
    public List<TubeDTO> parseTube(TubeDTO tubeDTO){
        return tubesParser.parse(tubeDTO,tube);
    }
}
