package com.example.IoTEnvMonitApp.service;

import jakarta.annotation.PostConstruct;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.stereotype.Service;


@Service
public class MqttService {

    private final MqttClient mqttClient;

    public MqttService(MqttClient mqttClient) {
        this.mqttClient = mqttClient;
    }

    @PostConstruct
    public void init() {
        try {
            // Subscribe to the relevant topics
            mqttClient.subscribe("rooms/auth/request", (topic, message) -> handleAuthRequest(message));
            mqttClient.subscribe("rooms/sensors/data", (topic, message) -> handleSensorData(message));

            // You can add more subscriptions if needed
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    // Method to handle publishing
    public void publish(String topic, String message) {
        try {
            MqttMessage mqttMessage = new MqttMessage(message.getBytes());
            mqttClient.publish(topic, mqttMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Handle messages from "rooms/auth/request"
    private void handleAuthRequest(MqttMessage message) {
        String payload = new String(message.getPayload());
        System.out.println("Received auth request: " + payload);

        // Example logic for publishing to "rooms/auth/response"
        String responsePayload = "{\"auth\":true,\"room\":1}";  // Adjust logic as per your app
        publish("rooms/auth/response", responsePayload);
    }

    // Handle messages from "rooms/sensors/data"
    private void handleSensorData(MqttMessage message) {
        String payload = new String(message.getPayload());
        System.out.println("Received sensor data: " + payload);

        // Example of publishing a message to "rooms/message"
        String responseMessage = "{\"room\":1, \"message\":\"Data received successfully\"}";
        publish("rooms/message", responseMessage);
    }

    // You can add more handlers here for other topics like "rooms/auth/cancel"
}
