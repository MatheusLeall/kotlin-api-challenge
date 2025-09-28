package com.tinnova.desafio.question05

import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class VehicleRequest(
    val veiculo: String,
    val marca: String,
    val ano: Int,
    val descricao: String,
    val vendido: Boolean
)

@Serializable
data class VehiclePatchRequest(
    val veiculo: String? = null,
    val marca: String? = null,
    val ano: Int? = null,
    val descricao: String? = null,
    val vendido: Boolean? = null
)

@Serializable
data class VehicleResponse(
    val id: Int,
    val veiculo: String,
    val marca: String,
    val ano: Int,
    val descricao: String,
    val vendido: Boolean,
    val created: String,
    val updated: String
)

@Serializable
data class VehiclesResponse(
    val vehicles: List<VehicleResponse>,
    val unsold_count: Int,
    val distribution_by_decade: Map<String, Int>,
    val distribution_by_brand: Map<String, Int>,
    val last_week_registered: List<VehicleResponse>
)


object VehicleRepository {
    private val vehicles = mutableListOf<VehicleResponse>()
    private var idCounter = 1

    // Marcas válidas
    private val allowedBrands = setOf("Ford", "Honda", "Toyota", "Volkswagen", "Chevrolet")

    fun isBrandValid(brand: String): Boolean =
        allowedBrands.contains(brand)

    fun getAll(marca: String? = null, ano: Int? = null): List<VehicleResponse> {
        return vehicles.filter { v ->
            (marca == null || v.marca.equals(marca, ignoreCase = true)) &&
                    (ano == null || v.ano == ano)
        }
    }

    fun getById(id: Int): VehicleResponse? = vehicles.find { it.id == id }

    fun add(vehicleRequest: VehicleRequest): VehicleResponse {
        if (!isBrandValid(vehicleRequest.marca)) {
            throw IllegalArgumentException("Marca inválida: ${vehicleRequest.marca}")
        }
        val newVehicle = VehicleResponse(
            id = idCounter++,
            veiculo = vehicleRequest.veiculo,
            marca = vehicleRequest.marca,
            ano = vehicleRequest.ano,
            descricao = vehicleRequest.descricao,
            vendido = vehicleRequest.vendido,
            created = LocalDateTime.now().toString(),
            updated = LocalDateTime.now().toString()
        )
        vehicles.add(newVehicle)
        return newVehicle
    }

    fun update(id: Int, vehicleRequest: VehicleRequest): VehicleResponse? {
        if (!isBrandValid(vehicleRequest.marca)) {
            throw IllegalArgumentException("Marca inválida: ${vehicleRequest.marca}")
        }
        val index = vehicles.indexOfFirst { it.id == id }
        if (index == -1) return null
        val updatedVehicle = VehicleResponse(
            id = id,
            veiculo = vehicleRequest.veiculo,
            marca = vehicleRequest.marca,
            ano = vehicleRequest.ano,
            descricao = vehicleRequest.descricao,
            vendido = vehicleRequest.vendido,
            created = vehicles[index].created,
            updated = LocalDateTime.now().toString()
        )
        vehicles[index] = updatedVehicle
        return updatedVehicle
    }

    fun partialUpdate(id: Int, patchRequest: VehiclePatchRequest): VehicleResponse? {
        val index = vehicles.indexOfFirst { it.id == id }
        if (index == -1) return null

        val current = vehicles[index]

        val patchedBrand = patchRequest.marca ?: current.marca
        if (!isBrandValid(patchedBrand)) {
            throw IllegalArgumentException("Marca inválida: $patchedBrand")
        }

        val patchedVehicle = current.copy(
            veiculo = patchRequest.veiculo ?: current.veiculo,
            marca = patchedBrand,
            ano = patchRequest.ano ?: current.ano,
            descricao = patchRequest.descricao ?: current.descricao,
            vendido = patchRequest.vendido ?: current.vendido,
            updated = LocalDateTime.now().toString()
        )

        vehicles[index] = patchedVehicle
        return patchedVehicle
    }

    fun delete(id: Int): Boolean = vehicles.removeIf { it.id == id }

    // ===== Regras de negócio extras =====
    fun getUnsoldCount(): Int = vehicles.count { !it.vendido }

    fun getDistributionByDecade(): Map<String, Int> {
        return vehicles.groupBy {
            val decade = (it.ano / 10) * 10
            "${decade}s"
        }.mapValues { it.value.size }
    }

    fun getDistributionByBrand(): Map<String, Int> {
        return vehicles.groupBy { it.marca }.mapValues { it.value.size }
    }

    fun getRegisteredLastWeek(): List<VehicleResponse> {
        val oneWeekAgo = LocalDateTime.now().minusWeeks(1)
        return vehicles.filter {
            LocalDateTime.parse(it.created).isAfter(oneWeekAgo)
        }
    }
}
