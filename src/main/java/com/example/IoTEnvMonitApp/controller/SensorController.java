package com.example.IoTEnvMonitApp.controller;

import com.example.IoTEnvMonitApp.model.SensorData;
import com.example.IoTEnvMonitApp.service.SensorService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sensor")
public class SensorController {

    private final SensorService sensorService;

    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @GetMapping("/data")
    public Iterable<SensorData> getSensorData() {
        return sensorService.getAllSensorData();
    }

    @PostMapping("/data")
    public SensorData saveSensorData(@RequestBody SensorData sensorData) {
        // Log the received data
        return sensorService.saveSensorData(sensorData);
    }
}

