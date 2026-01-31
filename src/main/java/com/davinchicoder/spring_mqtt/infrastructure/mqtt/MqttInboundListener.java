package com.davinchicoder.spring_mqtt.infrastructure.mqtt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MqttInboundListener {

    @ServiceActivator(inputChannel = "mqttInputChannel")
    public void handle(Message<String> message) {

        String payload = message.getPayload();
        String topic = message.getHeaders()
                .get(MqttHeaders.RECEIVED_TOPIC, String.class);

        if (topic == null) {
            log.error("No topic found in message: {}", payload);
            return;
        }

        TelemetryTopic telemetryTopic = TelemetryTopic.of(topic);
    }
}

