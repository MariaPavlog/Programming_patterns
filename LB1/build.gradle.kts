plugins {
    kotlin("jvm") version "2.0.0-RC1"
    kotlin("plugin.serialization") version "1.9.22"
    java
    id("org.openjfx.javafxplugin") version "0.1.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation ("org.junit.jupiter:junit-jupiter:5.7.0")
    testImplementation ("org.mockito:mockito-core:3.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
    implementation("com.charleskorn.kaml:kaml:0.61.0")
    implementation("org.xerial:sqlite-jdbc:3.45.1.0")
    implementation("org.slf4j:slf4j-reload4j:2.0.16")
    implementation("org.openjfx:javafx-controls:23.0.1")
    implementation("org.openjfx:javafx-fxml:23.0.1")
    implementation ("org.openjfx:javafx-base:23.0.1")

}
javafx {
    version = "23"
    modules = listOf("javafx.controls", "javafx.fxml") // Add more modules as needed


}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}