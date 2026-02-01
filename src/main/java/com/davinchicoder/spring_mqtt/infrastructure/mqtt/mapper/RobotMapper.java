package com.davinchicoder.spring_mqtt.infrastructure.mqtt.mapper;

import com.davinchicoder.spring_mqtt.domain.RobotCommand;
import com.davinchicoder.spring_mqtt.domain.RobotTelemetry;
import com.davinchicoder.spring_mqtt.infrastructure.mqtt.dto.RobotCommandDto;
import com.davinchicoder.spring_mqtt.infrastructure.mqtt.dto.RobotTelemetryDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface RobotMapper {

    RobotTelemetry toRobotTelemetry(RobotTelemetryDto dto);

    RobotCommand toRobotCommand(RobotCommandDto dto);

    RobotCommandDto toRobotCommandDto(RobotCommand dto);

}
