package com.davinchicoder.spring_mqtt.infrastructure.mqtt.config;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import java.util.UUID;

@EnableIntegration
@Configuration
public class MqttBrokerConfig {

    @Value("${app.mqtt.host}")
    private String host;

    @Value("${app.mqtt.client-id}")
    private String clientId;

    @Value("${app.mqtt.inbound.topic}")
    private String mqttInboundTopic;

    @Value("${app.mqtt.outbound.topic}")
    private String mqttOutboundTopic;


    @Bean
    public MqttPahoClientFactory mqttClientFactory() {

        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();

        MqttConnectOptions options = new MqttConnectOptions();
        options.setServerURIs(new String[]{host});
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(10);
        options.setMaxInflight(100);
        options.setKeepAliveInterval(20);

        factory.setConnectionOptions(options);
        return factory;
    }

    //Consumer
    @Bean
    public MessageChannel mqttInputChannel() {
        return new QueueChannel(10_000);
    }

    @Bean
    public MessageProducer mqttInbound() {
        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter(clientId, mqttClientFactory(), mqttInboundTopic);
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(0);
        adapter.setOutputChannel(mqttInputChannel());

        return adapter;
    }

    //Producer
    @Bean
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    public MessageHandler mqttOutbound(
            MqttPahoClientFactory factory
    ) {

        MqttPahoMessageHandler handler = new MqttPahoMessageHandler(
                clientId + UUID.randomUUID(),
                factory
        );

        handler.setAsync(true);
        handler.setDefaultQos(0);
        handler.setDefaultTopic(mqttOutboundTopic);

        return handler;
    }


}

