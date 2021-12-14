
package org.drones.dto;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JsonSupport {

    public Object convertToJson(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "";

        try {
            mapper.writeValueAsString(obj);
            jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonInString;
    }

    public <T> Object convertToObject(String json, T obj) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            obj = (T) mapper.readValue(json, (obj.getClass()));
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }


    public String convertToJsonRetString(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "";
        try {
            // Convert object to JSON string and save into a file directly
            mapper.writeValueAsString(obj);
            // Convert object to JSON string and pretty print
            jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonInString;
    }
}
