package com.davinchicoder.spring_mqtt.infrastructure.mqtt.producer;

import com.davinchicoder.spring_mqtt.domain.RobotCommand;
import com.davinchicoder.spring_mqtt.infrastructure.mqtt.dto.RobotCommandDto;
import com.davinchicoder.spring_mqtt.infrastructure.mqtt.mapper.RobotMapper;
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
    private final RobotMapper robotMapper;

    public void sendMessage(RobotCommand robotCommand) {
        log.info("Sending message: {}", robotCommand);

        RobotCommandDto robotCommandDto = robotMapper.toRobotCommandDto(robotCommand);

        boolean send = mqttOutboundChannel.send(
                MessageBuilder.withPayload(robotCommandDto).build()
        );

        if (!send) {
            log.error("Failed to send message {}", robotCommand);
        }
    }
}

