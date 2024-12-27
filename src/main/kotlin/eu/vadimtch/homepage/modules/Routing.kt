package eu.vadimtch.homepage.modules

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    install(StatusPages) {
        exception<Throwable> { call, _ ->
            call.respondText(text = "An unexpected error has occurred.", status = HttpStatusCode.InternalServerError)
        }
    }

    routing {
        get("/") {
            call.respondText("Welcome to vadimtch.eu!")
        }

        // staticResources("/static", "static")
    }
}
