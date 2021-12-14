package org.drones.parser;

import com.opencsv.bean.CsvToBeanBuilder;
import org.drones.dto.FlightPathDTO;
import org.drones.dto.TubeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Component
public class TubesParser extends ReaderCSV<TubeDTO> {

    @Autowired
    public TubesParser() {
        super(TubeDTO.class);
    }
    @Override
    public List<TubeDTO> parse(TubeDTO dto, String path) {
        List<TubeDTO> beans = new ArrayList<>();
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(path);
            File file = new File("temp.csv");
            Files.copy(inputStream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            FileReader reader = new FileReader(file);
            beans = new CsvToBeanBuilder<TubeDTO>(reader)
                    .withType(TubeDTO.class)
                    .build()
                    .parse();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return beans;
    }
}