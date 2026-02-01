package com.davinchicoder.spring_mqtt.infrastructure.mqtt.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class RobotCommandMqttProducer {

    private final MessageChannel mqttOutboundChannel;

    public void sendMessage(String message) {
        log.info("Sending message: {}", message);

        boolean send = mqttOutboundChannel.send(
                MessageBuilder.withPayload(message).build()
        );

        if (!send) {
            log.error("Failed to send message {}", message);
        }
    }
}

