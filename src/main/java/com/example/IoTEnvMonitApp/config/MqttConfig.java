package com.example.IoTEnvMonitApp.config;

import com.hivemq.client.mqtt.MqttClient;
import com.hivemq.client.mqtt.mqtt5.Mqtt5BlockingClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static com.hivemq.client.mqtt.MqttGlobalPublishFilter.ALL;
import static java.nio.charset.StandardCharsets.UTF_8;

@Configuration
public class MqttConfig {

    private static final String HOST = "594bf801d8c342e993ed74b68dbbc232.s1.eu.hivemq.cloud";  // Replace with your broker URL
    private static final int PORT = 8883;  // TLS-secured port for HiveMQ Cloud
    private static final String USERNAME = "web_client";  // Replace with your username
    private static final String PASSWORD = "Web32_client_pwd";  // Replace with your password

    @Bean
    public Mqtt5BlockingClient mqttClient() {
        // Create an MQTT client using MQTT 5
        Mqtt5BlockingClient client = MqttClient.builder()
                .useMqttVersion5()
                .serverHost(HOST)
                .serverPort(PORT)
                .sslWithDefaultConfig()  // Use SSL with default configuration
                .buildBlocking();

        // Connect to HiveMQ Cloud with TLS and username/password
        client.connectWith()
                .simpleAuth()
                .username(USERNAME)
                .password(UTF_8.encode(PASSWORD))
                .applySimpleAuth()
                .send();

        System.out.println("Connected successfully to HiveMQ");

        return client;
    }
}

