# Spring MQTT — Robot Telemetry

Aplicación Spring Boot que demuestra la integración con un broker MQTT utilizando Spring Integration para gestionar
telemetría de robots.

## Descripción

Este proyecto implementa un sistema de mensajería MQTT con las siguientes características:

- **Consumidor MQTT**: Escucha mensajes de telemetría de robots en el topic `robots/telemetry`
- **Productor MQTT**: Envía comandos a robots a través del topic `robots/commands`
- **Arquitectura Hexagonal**: Organización del código en capas (application, domain, infrastructure)

## Tecnologías

- **Java 25**
- **Spring Boot 4.0.2**
- **Spring Integration MQTT**
- **Eclipse Paho MQTT Client 1.2.5**
- **Lombok**
- **MapStruct 1.6.2**
- **Docker Compose** (para el broker Mosquitto)

## Requisitos Previos

- **JDK 25** o superior
- **Maven 3.9+**
- **Docker** y **Docker Compose**

## Configuración

## Ejecutar el Broker MQTT

Iniciar el broker Mosquitto con Docker Compose:

```bash 
docker compose up -d
```

Esto levantará:

- **Puerto 1883**: Protocolo MQTT
- **Puerto 9001**: WebSockets

Para detener el broker:

```bash
docker compose down
```

## Ejecutar la Aplicación

### Con Maven Wrapper

```bash
./mvnw spring-boot:run
```

### Compilar y ejecutar JAR

```bash
 ./mvnw clean package
  java -jar target/spring-mqtt-0.0.1-SNAPSHOT.jar
 ```

## 🧪 Probar la Conexión MQTT

### Publicar un mensaje de telemetría

Usando un cliente MQTT (como `mosquitto_pub`):

```bash
 mosquitto_pub -h localhost -p 1883 -t "robots/telemetry" -m '{"robotId": "robot-001", "battery": 85, "status": "active"}'
 ```

### Suscribirse a comandos

```bash
mosquitto_sub -h localhost -p 1883 -t "robots/commands"
```

## 🔧 Depuración

Para habilitar logs detallados de MQTT, descomentar en `application.yml` sección de logs

## 📚 Referencias

- [Spring Integration MQTT](https://docs.spring.io/spring-integration/reference/mqtt.html)
- [Eclipse Paho Java Client](https://eclipse.dev/paho/index.php?page=clients/java/index.php)
- [Eclipse Mosquitto](https://mosquitto.org/)

## 📄 Licencia

Este proyecto está disponible como código de ejemplo.
