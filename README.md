# Notification-System
Built a backend notification system using Spring Boot and Kafka for asynchronous Email, SMS, and Push notifications. Added retry mechanism, DLQ, scheduled notifications, rate limiting, and modular design using Strategy and Factory patterns for scalability and reliability.

spring.application.name=notification-system

spring.datasource.url=jdbc:mysql://localhost:3306/notification_system
spring.datasource.username=myUserName
spring.datasource.password=myPassword

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect

spring.kafka.bootstrap-servers=localhost:9092

#spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer
#spring.kafka.producer.value-serializer=org.apache.kafka.common.serialization.StringSerializer
spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer
spring.kafka.producer.value-serializer=org.springframework.kafka.support.serializer.JsonSerializer
spring.kafka.consumer.properties.spring.json.trusted.packages=com.notification.notification_system.event
spring.kafka.consumer.value-deserializer=org.springframework.kafka.support.serializer.JsonDeserializer
spring.kafka.consumer.properties.spring.json.value.default.type=com.notification.notification_system.event.NotificationEvent
#spring.kafka.producer.value-serializer=org.springframework.kafka.support.serializer.JsonSerializer
#spring.kafka.producer.key-serializer=org.springframework.kafka.support.serializer.JsonSerializer
#spring.kafka.consumer.properties.spring.json.trusted.packages=com.notification.notification_system.event
#spring.kafka.consumer.value-deserializer=org.springframework.kafka.support.serializer.JsonDeserializer
server.port=8080
