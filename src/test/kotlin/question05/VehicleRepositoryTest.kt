package question05

import com.tinnova.desafio.question05.VehiclePatchRequest
import com.tinnova.desafio.question05.VehicleRepository
import com.tinnova.desafio.question05.VehicleRequest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import org.junit.jupiter.api.BeforeEach

class VehicleRepositoryTest {

    @BeforeEach
    fun clearRepository() {
        // Limpa todos os veículos antes de cada teste
        val allVehicles = VehicleRepository.getAll()
        allVehicles.forEach { VehicleRepository.delete(it.id) }
    }

    @Test
    fun testAddVehicleValidBrand() {
        val vehicle = VehicleRequest(
            veiculo = "Civic",
            marca = "Honda",
            ano = 2015,
            descricao = "Carro sedã",
            vendido = false
        )
        val added = VehicleRepository.add(vehicle)
        assertEquals(vehicle.veiculo, added.veiculo)
        assertEquals(vehicle.marca, added.marca)
    }

    @Test
    fun testAddVehicleInvalidBrand() {
        val vehicle = VehicleRequest(
            veiculo = "Fusca",
            marca = "Forde", // marca inválida
            ano = 1975,
            descricao = "Clássico",
            vendido = false
        )
        assertFailsWith<IllegalArgumentException> {
            VehicleRepository.add(vehicle)
        }
    }

    @Test
    fun testUpdateVehicle() {
        val vehicle = VehicleRequest("Corolla", "Toyota", 2018, "Sedã", false)
        val added = VehicleRepository.add(vehicle)
        val updatedRequest = VehicleRequest("Corolla X", "Toyota", 2018, "Sedã completo", true)
        val updated = VehicleRepository.update(added.id, updatedRequest)
        assertEquals(updatedRequest.veiculo, updated?.veiculo)
        assertEquals(updatedRequest.vendido, updated?.vendido)
    }

    @Test
    fun testPartialUpdateVehicle() {
        val vehicle = VehicleRequest("Fiesta", "Ford", 2017, "Hatch", false)
        val added = VehicleRepository.add(vehicle)
        val patch = VehiclePatchRequest(vendido = true)
        val patched = VehicleRepository.partialUpdate(added.id, patch)
        assertEquals(true, patched?.vendido)
        assertEquals(added.veiculo, patched?.veiculo) // campos não alterados permanecem iguais
    }

    @Test
    fun testDeleteVehicle() {
        val vehicle = VehicleRequest("HRV", "Honda", 2019, "SUV", false)
        val added = VehicleRepository.add(vehicle)
        val deleted = VehicleRepository.delete(added.id)
        assertEquals(true, deleted)
        assertEquals(null, VehicleRepository.getById(added.id))
    }

    @Test
    fun testGetAllWithFilters() {
        VehicleRepository.add(VehicleRequest("Onix", "Chevrolet", 2020, "Hatch", false))
        VehicleRepository.add(VehicleRequest("Camry", "Toyota", 2020, "Sedã", true))

        val all = VehicleRepository.getAll()
        val byMarca = VehicleRepository.getAll(marca = "Toyota")
        val byAno = VehicleRepository.getAll(ano = 2020)

        assert(all.size >= 2)
        assertEquals(1, byMarca.size)
        assertEquals(2, byAno.size)
    }

    @Test
    fun testBusinessRules() {
        VehicleRepository.add(VehicleRequest("Focus", "Ford", 2010, "Hatch", false))
        VehicleRepository.add(VehicleRequest("Civic", "Honda", 2015, "Sedã", true))

        val unsold = VehicleRepository.getUnsoldCount()
        val byDecade = VehicleRepository.getDistributionByDecade()
        val byBrand = VehicleRepository.getDistributionByBrand()
        val lastWeek = VehicleRepository.getRegisteredLastWeek()

        assert(unsold >= 1)
        assert(byDecade.isNotEmpty())
        assert(byBrand.isNotEmpty())
        assert(lastWeek.isNotEmpty())
    }
}
