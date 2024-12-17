package com.example.IoTEnvMonitApp.config;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqttConfig {

    private static final String BROKER_URL = "tcp://broker.hivemq.com:1883";  // Example broker
    private static final String CLIENT_ID = "spring_mqtt_client";

    @Bean
    public MqttClient mqttClient() throws MqttException {
        MqttClient mqttClient = new MqttClient(BROKER_URL, CLIENT_ID, new MemoryPersistence());

        // Create and set connection options (if necessary)
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(true);
        options.setKeepAliveInterval(60);

        // Connect to the MQTT broker
        mqttClient.connect(options);

        return mqttClient;
    }
}

