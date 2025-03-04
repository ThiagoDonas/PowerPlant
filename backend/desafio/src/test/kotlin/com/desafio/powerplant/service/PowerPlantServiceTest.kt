package com.desafio.powerplant.service

import com.desafio.powerplant.model.PowerPlant
import com.desafio.powerplant.repository.PowerPlantRepository
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.verify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.ActiveProfiles
import java.time.LocalDate


@ExtendWith(MockKExtension::class)
@ActiveProfiles("test")
class PowerPlantServiceTest {

    @MockK
    private lateinit var repository: PowerPlantRepository

    @InjectMockKs
    private lateinit var plantService: PowerPlantService

    private val mockPowerPlants = listOf(
        PowerPlant(1,"UTE.GN.RJ.038173-0.1", "GNA II (Antiga GNA Porto do Açu III)", "RJ", "Fóssil", "UTE", 1672599.0, "Em andamento",
            LocalDate.parse("2022-03-01"), LocalDate.parse("2025-01-27"), "Subestação", "NEOENERGIA GUANABARA TRANSMISSAO DE ENERGIA S.A.", 500.0, LocalDate.parse("2025-02-20")),
        PowerPlant(2,"UTE.GN.CE.037748-1.1", "Portocém I", "CE", "Fóssil", "UTE", 1571888.0, "Não Iniciada",
            null, null, "Subestação", "Cia. Hidro Elétrica do São Francisco", 500.0, LocalDate.parse("2024-03-01")),
        PowerPlant(3,"UTE.GN.PA.037748-1.2", "Portocém I", "PA", "Fóssil", "UTE", 1571888.0, "Em andamento",
            LocalDate.parse("2024-08-28"), null, "Subestação", "CENTRAIS ELETRICAS DO NORTE DO BRASIL S/A ELETRONORTE", 500.0, LocalDate.parse("2025-02-20")),
        PowerPlant(4,"UTN.UR.RJ.030150-7.1", "Almirante Álvaro Alberto - Unidade III (Antiga Angra III)", "RJ", "Nuclear", "UTN", 1350000.0, "Paralisada",
            LocalDate.parse("2009-12-12"), null, "Subestação", "FURNAS - CENTRAIS ELETRICAS S.A.", 500.0, LocalDate.parse("2025-02-20")),
        PowerPlant(5,"UTE.GN.RJ.032955-0.2", "GNA I (Antiga Novo Tempo GNA II)", "RJ", "Fóssil", "UTE", 1338300.0, "Em andamento",
            LocalDate.parse("2018-05-15"), LocalDate.parse("2021-01-08"), "Subestação", "FURNAS-Centrais Elétricas S/A", 345.0, LocalDate.parse("2021-09-15"))
    )

    @Test
    fun `should return top 5 largest power plants`() {
        // Given
        every { repository.findTop5ByOrderByCapacityDesc() } returns mockPowerPlants

        // When
        val result = plantService.getTop5LargestPlants()

        // Then
        assertEquals(5, result.size)
        assertEquals("GNA II (Antiga GNA Porto do Açu III)", result[0].name)
        assertEquals("Portocém I", result[1].name)
        assertEquals("Portocém I", result[2].name)
        assertEquals("Almirante Álvaro Alberto - Unidade III (Antiga Angra III)", result[3].name)
        assertEquals("GNA I (Antiga Novo Tempo GNA II)", result[4].name)

        verify(exactly = 1) { repository.findTop5ByOrderByCapacityDesc() }
    }

    @Test
    fun `should return all power plants`() {
        // Given
        every { repository.findAll() } returns mockPowerPlants

        // When
        val result = plantService.getAllPowerPlants()

        // Then
        assertEquals(5, result.size)
        assertEquals("GNA II (Antiga GNA Porto do Açu III)", result[0].name)
        assertEquals("Portocém I", result[1].name)

        verify(exactly = 1) { repository.findAll() }
    }
}
