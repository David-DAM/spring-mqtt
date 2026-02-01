package com.davinchicoder.spring_mqtt.infrastructure.mqtt.consumer;

import com.davinchicoder.spring_mqtt.domain.RobotTelemetry;
import com.davinchicoder.spring_mqtt.infrastructure.mqtt.dto.RobotTelemetryDto;
import com.davinchicoder.spring_mqtt.infrastructure.mqtt.mapper.RobotMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.stereotype.Component;
import tools.jackson.databind.json.JsonMapper;

@Slf4j
@RequiredArgsConstructor
@Component
public class RobotTelemetryMqttConsumer implements MessageHandler {

    private final JsonMapper jsonMapper;
    private final RobotMapper robotMapper;

    @ServiceActivator(inputChannel = "mqttInputChannel")
    @Override
    public void handleMessage(Message<?> message) {
        log.info("Received message: {}", message);

        String payload = message.getPayload().toString();
        String topic = message.getHeaders()
                .get(MqttHeaders.RECEIVED_TOPIC, String.class);

        RobotTelemetryDto robotTelemetryDto = jsonMapper.readValue(payload, RobotTelemetryDto.class);

        RobotTelemetry robotTelemetry = robotMapper.toRobotTelemetry(robotTelemetryDto);

        log.info("Robot telemetry: {}", robotTelemetryDto);
    }
}

