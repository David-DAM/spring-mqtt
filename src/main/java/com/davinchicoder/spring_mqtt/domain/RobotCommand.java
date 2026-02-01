package com.davinchicoder.spring_mqtt.domain;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Builder
@Data
public class RobotCommand {

    private String id;
    private String action;
    private Instant timestamp;

}
