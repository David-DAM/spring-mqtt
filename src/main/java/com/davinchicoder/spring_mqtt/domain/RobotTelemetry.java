package com.davinchicoder.spring_mqtt.domain;

import com.davinchicoder.spring_mqtt.infrastructure.mqtt.dto.PositionDto;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Builder
@Data
public class RobotTelemetry {
    private String id;
    private double battery;
    private double temperature;
    private PositionDto position;
    private Instant timestamp;

}
