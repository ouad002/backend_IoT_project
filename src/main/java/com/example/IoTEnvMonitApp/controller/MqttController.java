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
    public String publishToTopic(@RequestParam String topic, @RequestBody String message) {
        mqttService.publish(topic, message);
        return "Message published to topic: " + topic;
    }
}
