package com.davinchicoder.spring_mqtt.infrastructure.databse;

import lombok.Data;

@Data
public class RobotEntity {

    private String id;
    private String name;
    private String status;
    private String location;

}
