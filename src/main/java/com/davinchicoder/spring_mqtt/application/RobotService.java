package com.davinchicoder.spring_mqtt.application;

import com.davinchicoder.spring_mqtt.domain.RobotActionType;
import com.davinchicoder.spring_mqtt.domain.RobotCommand;
import com.davinchicoder.spring_mqtt.domain.RobotTelemetry;
import com.davinchicoder.spring_mqtt.infrastructure.mqtt.producer.RobotCommandMqttProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class RobotService {

    private final RobotCommandMqttProducer robotCommandMqttProducer;

    public void consumeTelemetry(RobotTelemetry telemetry) {

        if (telemetry.getBattery() < 10) {
            log.info("Battery low, sending charge command");
            robotCommandMqttProducer.sendMessage(new RobotCommand(RobotActionType.CHARGE));
            return;
        }

        if (telemetry.getTemperature() > 30) {
            log.info("Temperature too high, sending turn off command");
            robotCommandMqttProducer.sendMessage(new RobotCommand(RobotActionType.TURN_OFF));
        }

    }


}
