package org.drones.parser;

import com.opencsv.bean.CsvToBeanBuilder;
import org.drones.dto.FlightPathDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Component
public class FlightPathParser extends ReaderCSV<FlightPathDTO> {
    @Autowired
    public FlightPathParser() {
        super(FlightPathDTO.class);
    }

    @Override
    public List<FlightPathDTO> parse(FlightPathDTO dto, String path) {
        List<FlightPathDTO> beans = new ArrayList<>();
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(path);
            File file = new File("temp.csv");
            Files.copy(inputStream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            FileReader reader = new FileReader(file);
            beans = new CsvToBeanBuilder<FlightPathDTO>(reader)
                    .withType(FlightPathDTO.class)
                    .build()
                    .parse();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return beans;

    }

}
