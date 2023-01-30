import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    war
    java
    id("org.springframework.boot") version "3.0.0"
    id ("com.google.cloud.tools.appengine-appyaml") version "2.4.4"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.7.21"
    kotlin("plugin.spring") version "1.7.21"
}

buildscript {
    repositories {
        mavenCentral()
    }

    dependencies {
        classpath ("com.google.cloud.tools:appengine-gradle-plugin:2.4.4")
    }
}



group = "com.tim"
version = "0.0.1"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}



appengine{
    deploy {
        projectId = "GCLOUD_CONFIG"
        version = "GCLOUD_CONFIG"
    }
}
dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation ("com.google.code.gson:gson:2.10")
    implementation("org.apache.commons:commons-lang3:3.12.0")
    //providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.mockk:mockk:1.8.8")
}

springBoot {
    mainClass.set("com.tim.quiz_api.QuizApiApplicationKt")
}

configurations {
    all {
        exclude(module = "spring-boot-starter-logging")
        exclude(module = "logback-classic")
        //exclude(module = "spring-boot-starter-tomcat")

    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}


tasks.getByName<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    manifest {
        attributes("Start-Class" to "com.tim.quiz_api.QuizApiApplicationKt")
    }
    enabled =true
}

tasks.getByName<Jar>("jar") {
    archiveClassifier.set("")
    manifest {
        attributes("Main-Class" to "com.tim.quiz_api.QuizApiApplicationKt")

    }

}