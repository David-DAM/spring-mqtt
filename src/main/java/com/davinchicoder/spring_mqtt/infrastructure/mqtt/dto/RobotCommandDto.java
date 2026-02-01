package com.davinchicoder.spring_mqtt.infrastructure.mqtt.dto;

import java.time.Instant;

public record RobotCommandDto(
        String id,
        String action,
        Instant timestamp
) {

}
