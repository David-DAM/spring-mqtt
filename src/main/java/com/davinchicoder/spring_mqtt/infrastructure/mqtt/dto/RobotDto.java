package com.davinchicoder.spring_mqtt.infrastructure.mqtt.dto;

import lombok.Data;

@Data
public class RobotDto {

    private String id;
    private String name;
    private String status;
    private String location;

}
