package com.desafio.powerplant.controller

import com.desafio.powerplant.service.PowerPlantService
import com.desafio.powerplant.model.PowerPlant
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException


@RestController
@RequestMapping("/power_plants")
@Tag(name = "Power Plants", description = "Endpoints to manage power plants")
class PowerPlantController(val service: PowerPlantService) {

    @GetMapping("/top5")
    @Operation(summary = "List the top 5 largest power plants")
    fun getTop5(): ResponseEntity<List<PowerPlant>> {
        val powerPlants =  service.getTop5LargestPlants()
        return if (powerPlants.isEmpty()) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.ok(powerPlants)
        }
    }

    @GetMapping
    @Operation(summary = "List all power plants")
    fun getAll(): ResponseEntity<List<PowerPlant>> {
       val powerPlants = service.getAllPowerPlants()
        return if (powerPlants.isEmpty()) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.ok(powerPlants)
        }
    }

    @PostMapping("/download")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Trigger manual CSV download and process")
    fun triggerCsvDownload() {
        try {
            service.downloadAndProcessCsv()
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to process CSV", e)
        }
    }
}