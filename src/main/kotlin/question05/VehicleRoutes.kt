package com.tinnova.desafio.question05

import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.http.HttpStatusCode

fun Route.vehicleRoutes() {
    route("/vehicles") {
        get {
            val marca = call.request.queryParameters["marca"]
            val ano = call.request.queryParameters["ano"]?.toIntOrNull()

            val vehicles = VehicleRepository.getAll(marca, ano)

            val response = VehiclesResponse(
                vehicles = vehicles,
                unsold_count = VehicleRepository.getUnsoldCount(),
                distribution_by_decade = VehicleRepository.getDistributionByDecade(),
                distribution_by_brand = VehicleRepository.getDistributionByBrand(),
                last_week_registered = VehicleRepository.getRegisteredLastWeek()
            )

            call.respond(HttpStatusCode.OK, response)
        }
        get("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
            val vehicle = id?.let { VehicleRepository.getById(it) }
            if (vehicle == null) call.respond(mapOf("error" to "Vehicle not found"))
            else call.respond(vehicle)
        }
        post {
            try {
                val vehicle = call.receive<VehicleRequest>()
                val newVehicle = VehicleRepository.add(vehicle)
                call.respond(newVehicle)
            } catch (e: IllegalArgumentException) {
                call.respond(HttpStatusCode.BadRequest, mapOf("error" to e.message))
            }
        }
        put("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
            if (id == null) {
                call.respond(mapOf("error" to "Invalid ID"))
                return@put
            }
            try {
                val vehicle = call.receive<VehicleRequest>()
                val updated = VehicleRepository.update(id, vehicle)
                if (updated == null) call.respond(mapOf("error" to "Vehicle not found"))
                else call.respond(updated)
            } catch (e: IllegalArgumentException) {
                call.respond(HttpStatusCode.BadRequest, mapOf("error" to e.message))
            }
        }
        patch("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, "ID inválido")
                return@patch
            }

            try {
                val patchRequest = call.receive<VehiclePatchRequest>()
                val updatedVehicle = VehicleRepository.partialUpdate(id, patchRequest)

                if (updatedVehicle == null) {
                    call.respond(HttpStatusCode.NotFound, "Veículo não encontrado")
                } else {
                    call.respond(HttpStatusCode.OK, updatedVehicle)
                }
            } catch (e: IllegalArgumentException) {
                call.respond(HttpStatusCode.BadRequest, mapOf("error" to e.message))
            }
        }
        delete("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
            if (id == null) {
                call.respond(mapOf("error" to "Invalid ID"))
                return@delete
            }
            val deleted = VehicleRepository.delete(id)
            if (!deleted) call.respond(mapOf("error" to "Vehicle not found"))
            else call.respond(mapOf("success" to true))
        }
    }
}
