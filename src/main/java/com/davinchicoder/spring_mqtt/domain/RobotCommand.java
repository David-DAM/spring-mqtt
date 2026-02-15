package com.davinchicoder.spring_mqtt.domain;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class RobotCommand {

    private String id;
    private RobotActionType action;
    private Instant timestamp;

    public RobotCommand(RobotActionType action) {
        this.timestamp = Instant.now();
        this.id = UUID.randomUUID().toString();
        this.action = action;
    }

}
