import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.5.5"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.5.31"
    kotlin("plugin.spring") version "1.5.31"
    id("org.jetbrains.kotlin.plugin.jpa") version "1.3.61"
    kotlin("plugin.serialization") version "1.5.0"
    kotlin("kapt") version "1.5.31"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.0")
    implementation("org.hibernate:hibernate-core")
    implementation("org.projectlombok:lombok")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation") // NotEmpty

    implementation("org.flywaydb:flyway-core:7.10.0")
    compileOnly("org.projectlombok:lombok")

    // retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:3.9.0")

    // aws sqs
    implementation(platform("software.amazon.awssdk:bom:2.15.0"))
    implementation("org.springframework.cloud:spring-cloud-aws-messaging:2.2.1.RELEASE")

    // MapStruct
    implementation("org.mapstruct:mapstruct:1.4.2.Final")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.4.2.Final")
    annotationProcessor(
        "org.projectlombok:lombok-mapstruct-binding:0.2.0"
    )
    kapt("org.mapstruct:mapstruct-processor:1.4.2.Final")

    annotationProcessor(
        "org.projectlombok:lombok"
    )

    implementation("com.google.guava:guava:28.2-jre")
    implementation("org.apache.commons:commons-lang3:3.9")

    runtimeOnly("com.h2database:h2")
    runtimeOnly("mysql:mysql-connector-java")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}