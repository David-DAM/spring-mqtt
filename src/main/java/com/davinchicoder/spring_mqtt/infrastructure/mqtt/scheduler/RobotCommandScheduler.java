package com.davinchicoder.spring_mqtt.infrastructure.mqtt.scheduler;

import com.davinchicoder.spring_mqtt.domain.RobotCommand;
import com.davinchicoder.spring_mqtt.infrastructure.mqtt.producer.RobotCommandMqttProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class RobotCommandScheduler {

    private final RobotCommandMqttProducer robotCommandMqttProducer;

    @Scheduled(cron = "0/5 * * * * *")
    public void sendRobotCommand() {
        log.info("Sending robot command");

        RobotCommand robotCommand = RobotCommand.builder()
                .build();

        robotCommandMqttProducer.sendMessage(robotCommand);

        log.info("Robot command sent");
    }


}
