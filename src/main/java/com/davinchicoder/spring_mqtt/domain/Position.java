package com.davinchicoder.spring_mqtt.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Position {

    private String x;
    private String y;

}
