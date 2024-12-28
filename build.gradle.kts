val kotlinVersion: String by project
val logbackVersion: String by project

plugins {
    kotlin("jvm") version "2.1.0"
    id("io.ktor.plugin") version "3.0.2"
}

group = "eu.vadimtch.homepage"
version = "1.0.0"

application {
    mainClass.set("eu.vadimtch.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

ktor {
    docker {
        localImageName.set("ghcr.io/vadim-tch/homepage")
        imageTag.set("latest")

        jreVersion.set(JavaVersion.VERSION_23)

        portMappings.set(listOf(
            io.ktor.plugin.features.DockerPortMapping(
                outsideDocker = 8080,
                insideDocker = 80,
                protocol = io.ktor.plugin.features.DockerPortMappingProtocol.TCP
            )
        ))
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-host-common-jvm")
    implementation("io.ktor:ktor-server-status-pages-jvm")
    implementation("io.ktor:ktor-server-call-logging-jvm")
    implementation("io.ktor:ktor-server-netty-jvm")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    testImplementation("io.ktor:ktor-server-test-host-jvm")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion")
}
