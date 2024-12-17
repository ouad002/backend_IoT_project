package com.example.IoTEnvMonitApp.service;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Service;

@Service
public class MqttService {

    private final MqttClient mqttClient;

    public MqttService(MqttClient mqttClient) {
        this.mqttClient = mqttClient;
    }

    public void publish(String sensorData) {
        try {
            MqttMessage message = new MqttMessage(sensorData.getBytes());
            mqttClient.publish("rooms/sensors/data", message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


