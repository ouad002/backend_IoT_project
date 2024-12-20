package com.example.IoTEnvMonitApp.service;

import com.hivemq.client.mqtt.mqtt5.Mqtt5BlockingClient;
import com.hivemq.client.mqtt.mqtt5.Mqtt5AsyncClient;
import com.hivemq.client.mqtt.datatypes.MqttQos;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class MqttService {

    private final Mqtt5AsyncClient mqttClient;

    public MqttService(Mqtt5BlockingClient mqttBlockingClient) {
        // Convert the blocking client to an async client for non-blocking operations
        this.mqttClient = mqttBlockingClient.toAsync();
    }

    @PostConstruct
    public void init() {
        // Subscribe to the relevant topics asynchronously
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

        // Add more subscriptions as needed
    }

    // Method to handle publishing
    public void publish(String topic, String message) {
        mqttClient.publishWith()
                .topic(topic)
                .payload(message.getBytes(StandardCharsets.UTF_8))
                .qos(MqttQos.AT_LEAST_ONCE)
                .send();
    }

    // Method to publish to "rooms/auth/cancel"
    public void publishAuthCancel(int roomId) {
        String cancelMessage = String.format("{\"room\":%d}", roomId);
        publish("rooms/auth/cancel", cancelMessage);
    }

    // Handle messages from "rooms/auth/request"
    private void handleAuthRequest(String payload) {
        System.out.println("Received auth request: " + payload);

        // Example logic for publishing to "rooms/auth/response"
        String responsePayload = "{\"auth\":true,\"room\":1}";  // Adjust logic as per your app
        publish("rooms/auth/response", responsePayload);
    }

    // Handle messages from "rooms/sensors/data"
    private void handleSensorData(String payload) {
        System.out.println("Received sensor data: " + payload);

        // Example of publishing a message to "rooms/message"
        String responseMessage = "{\"room\":1, \"message\":\"Data received successfully\"}";
        publish("rooms/message", responseMessage);
    }


}

