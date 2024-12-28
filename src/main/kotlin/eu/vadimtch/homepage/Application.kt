package eu.vadimtch.homepage

import eu.vadimtch.homepage.modules.configureMonitoring
import eu.vadimtch.homepage.modules.configureRouting
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    // This should respond on both IPv4 and IPv6
    embeddedServer(Netty, port = 80, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureMonitoring()
    configureRouting()
}
