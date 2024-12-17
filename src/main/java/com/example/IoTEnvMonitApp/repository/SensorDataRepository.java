package com.example.IoTEnvMonitApp.repository;

import com.example.IoTEnvMonitApp.model.SensorData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorDataRepository extends JpaRepository<SensorData, Long> {
}
