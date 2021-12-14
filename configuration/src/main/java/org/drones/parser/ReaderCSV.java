package org.drones.parser;

import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.List;

public class ReaderCSV<T extends Serializable> {
    private Class<T> dtoClass;

    public ReaderCSV(Class<T> dtoClass) {
        super();
        this.dtoClass = dtoClass;
    }
    public ReaderCSV(){
        super();
    }


    public List<T> parse(T dto, String file){
        return  (List<T>) dto;
    }
}
