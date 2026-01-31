package com.davinchicoder.spring_mqtt.infrastructure.mqtt;

public record TelemetryTopic(
        String tenantId,
        String robotId
) {
    public static TelemetryTopic of(String topic) {
        String[] p = topic.split("/");
        return new TelemetryTopic(p[1], p[2]);
    }
}

