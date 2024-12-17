package com.example.IoTEnvMonitApp.controller;

import com.example.IoTEnvMonitApp.service.MqttService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mqtt")
public class MqttController {

    private final MqttService mqttService;

    public MqttController(MqttService mqttService) {
        this.mqttService = mqttService;
    }

    @PostMapping("/publish")
    public String publishSensorData(@RequestBody String sensorData) {
        mqttService.publish(sensorData);
        return "Data published";
    }
}

