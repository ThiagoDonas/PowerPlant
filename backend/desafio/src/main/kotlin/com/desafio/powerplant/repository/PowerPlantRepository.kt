package com.desafio.powerplant.repository

import com.desafio.powerplant.model.PowerPlant
import org.springframework.data.jpa.repository.JpaRepository

interface PowerPlantRepository : JpaRepository<PowerPlant, Long> {
    fun findTop5ByOrderByCapacityDesc(): List<PowerPlant>

    fun findByCodeCEG(codeCEG: String): PowerPlant?
}
