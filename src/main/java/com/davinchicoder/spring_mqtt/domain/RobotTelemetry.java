package com.davinchicoder.spring_mqtt.domain;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Builder
@Data
public class RobotTelemetry {
    private String id;
    private double battery;
    private double temperature;
    private Position position;
    private Instant timestamp;

}
