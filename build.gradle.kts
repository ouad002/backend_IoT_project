plugins {
	java
	id("org.springframework.boot") version "3.4.0"
	id("io.spring.dependency-management") version "1.1.6"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	// Spring Boot Starter dependencies
	implementation("org.springframework.boot:spring-boot-starter-web") // For REST APIs
	implementation("org.springframework.boot:spring-boot-starter-data-jpa") // For JPA and Hibernate
	implementation("org.springframework.boot:spring-boot-starter-security") // For Spring Security

	// H2 Database dependency
	implementation("com.h2database:h2") // For in-memory H2 database

	// MQTT dependencies
	implementation("org.eclipse.paho:org.eclipse.paho.client.mqttv3:1.2.5") // For MQTT communication
	implementation ("com.hivemq:hivemq-mqtt-client:1.2.1")

	// Jakarta Persistence API (JPA)
	implementation("jakarta.persistence:jakarta.persistence-api:3.1.0") // Required for JPA annotations

	// Swagger dependencies
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")

	// Testing dependencies
	testImplementation("org.springframework.boot:spring-boot-starter-test") // For testing
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	testImplementation("org.springframework.security:spring-security-test") // For Spring Security testing
}

tasks.withType<Test> {
	useJUnitPlatform()
}



