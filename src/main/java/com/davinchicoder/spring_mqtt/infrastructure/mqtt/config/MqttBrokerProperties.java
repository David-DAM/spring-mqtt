package com.davinchicoder.spring_mqtt.infrastructure.mqtt.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "app.mqtt")
@Data
@Component
public class MqttBrokerProperties {

    private String host;
    private String clientId;
    private String inboundTopic;
    private String outboundTopic;
}
