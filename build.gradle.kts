plugins {
    kotlin("jvm") version "2.2.10"
    kotlin("plugin.serialization") version "2.2.10"
    application
}

group = "com.tinnova.desafio"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Ktor core + Netty engine — atualizadas para 3.2.3
    implementation("io.ktor:ktor-server-core:3.2.3")
    implementation("io.ktor:ktor-server-netty:3.2.3")

    // Content negotiation (JSON) — versão correspondente
    implementation("io.ktor:ktor-server-content-negotiation:3.2.3")
    implementation("io.ktor:ktor-serialization-kotlinx-json:3.2.3")

    // Módulo de testes atualizado
    testImplementation("io.ktor:ktor-server-test-host:3.2.3")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:2.2.10")

    testImplementation(kotlin("test"))
}

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}

tasks.test {
    useJUnitPlatform()
}
