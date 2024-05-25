plugins {
	java
	jacoco
	id("org.springframework.boot") version "3.2.4"
	id("io.spring.dependency-management") version "1.1.4"
	id("org.sonarqube") version "5.0.0.4638"
}

group = "id.ac.ui.cs.advprog"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("io.micrometer:micrometer-registry-prometheus")
	implementation("org.postgresql:postgresql")

	compileOnly("org.projectlombok:lombok")

	developmentOnly("org.springframework.boot:spring-boot-devtools")

	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
	annotationProcessor("org.projectlombok:lombok")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.test {
	finalizedBy(tasks.jacocoTestReport) // Ensure jacocoTestReport runs after tests
}

tasks.jacocoTestReport {
	dependsOn(tasks.test) // Ensure tests run before generating the report
	reports {
		xml.required.set(true)
		csv.required.set(false)
		html.required.set(true)
		html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))
	}
	classDirectories.setFrom(files(classDirectories.files.map {
		fileTree(it) {
			exclude("**/*Application**")
		}
	}))
}

sonarqube {
	properties {
		property("sonar.projectKey", "Adpro-C4_microservice-voucher")
		property("sonar.organization", "adpro-c4")
		property("sonar.host.url", "https://sonarcloud.io")
		property("sonar.junit.reportPaths", "build/test-results/test")
		property("sonar.jacoco.reportPaths", "build/jacoco/test.exec")
	}
}

tasks.named("sonar") {
	dependsOn("test")
}
