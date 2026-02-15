package com.davinchicoder.spring_mqtt.infrastructure.mqtt.dto;

import java.time.Instant;

public record RobotTelemetryDto(
        String id,
        double battery,
        double temperature,
        String position,
        Instant timestamp
) {

}
