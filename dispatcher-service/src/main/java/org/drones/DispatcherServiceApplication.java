package org.drones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class DispatcherServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(DispatcherServiceApplication.class, args);
    }


}
