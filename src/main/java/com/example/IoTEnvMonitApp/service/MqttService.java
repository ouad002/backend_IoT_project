package com.example.IoTEnvMonitApp.service;

import com.example.IoTEnvMonitApp.model.SensorData;
import com.example.IoTEnvMonitApp.service.SensorService;
import com.hivemq.client.mqtt.mqtt5.Mqtt5BlockingClient;
import com.hivemq.client.mqtt.mqtt5.Mqtt5AsyncClient;
import com.hivemq.client.mqtt.datatypes.MqttQos;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MqttService {

    private final Mqtt5AsyncClient mqttClient;
    private final SensorService sensorService;
    private final ObjectMapper objectMapper;

    public MqttService(Mqtt5BlockingClient mqttBlockingClient, SensorService sensorService) {
        this.mqttClient = mqttBlockingClient.toAsync();
        this.sensorService = sensorService;
        this.objectMapper = new ObjectMapper();
    }

    @PostConstruct
    public void init() {
        mqttClient.subscribeWith()
                .topicFilter("rooms/auth/request")
                .qos(MqttQos.AT_LEAST_ONCE)
                .callback(publish -> handleAuthRequest(new String(publish.getPayloadAsBytes(), StandardCharsets.UTF_8)))
                .send();

        mqttClient.subscribeWith()
                .topicFilter("rooms/sensors/data")
                .qos(MqttQos.AT_LEAST_ONCE)
                .callback(publish -> handleSensorData(new String(publish.getPayloadAsBytes(), StandardCharsets.UTF_8)))
                .send();
    }

    public void publish(String topic, String message) {
        mqttClient.publishWith()
                .topic(topic)
                .payload(message.getBytes(StandardCharsets.UTF_8))
                .qos(MqttQos.AT_LEAST_ONCE)
                .send();
    }

    public void publishAuthCancel(int roomId) {
        String cancelMessage = String.format("{\"room\":%d}", roomId);
        publish("rooms/auth/cancel", cancelMessage);
    }

    private void handleAuthRequest(String payload) {
        System.out.println("Received auth request: " + payload);
        String responsePayload = "{\"auth\":true,\"room\":1}";
        publish("rooms/auth/response", responsePayload);
    }

    private void handleSensorData(String payload) {
        System.out.println("Received sensor data: " + payload);
        try {
            SensorData sensorData = objectMapper.readValue(payload, SensorData.class);
            sensorService.saveSensorData(sensorData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

