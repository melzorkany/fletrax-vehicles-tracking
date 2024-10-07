package com.fletrax;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VehicleTrackingApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(VehicleTrackingApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // This method can be used for testing or setup at startup
        System.out.println("Vehicle Tracking Application Started!");
    }

}
