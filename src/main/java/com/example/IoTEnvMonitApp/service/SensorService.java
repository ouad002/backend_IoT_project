package com.example.IoTEnvMonitApp.service;

import com.example.IoTEnvMonitApp.model.SensorData;
import com.example.IoTEnvMonitApp.repository.SensorDataRepository;
import org.springframework.stereotype.Service;

@Service
public class SensorService {

    private final SensorDataRepository sensorDataRepository;

    public SensorService(SensorDataRepository sensorDataRepository) {
        this.sensorDataRepository = sensorDataRepository;
    }

    public Iterable<SensorData> getAllSensorData() {
        return sensorDataRepository.findAll();
    }

    public SensorData saveSensorData(SensorData sensorData) {
        return sensorDataRepository.save(sensorData);
    }
}

