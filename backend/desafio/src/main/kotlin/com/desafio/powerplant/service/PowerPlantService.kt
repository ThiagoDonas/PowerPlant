package com.desafio.powerplant.service

import com.desafio.powerplant.model.PowerPlant
import com.desafio.powerplant.repository.PowerPlantRepository
import com.desafio.utils.CommonUtils.convertToDouble
import com.desafio.utils.CommonUtils.convertToUtf8
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.io.StringReader
import java.time.LocalDate
import com.opencsv.CSVReaderBuilder
import com.opencsv.CSVParserBuilder
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.net.URI
import org.slf4j.LoggerFactory

@Service
class PowerPlantService(val repository: PowerPlantRepository) {

    private val logger = LoggerFactory.getLogger(PowerPlantService::class.java)
    private val csvUrl = "https://dadosabertos.aneel.gov.br/dataset/57e4b8b5-a5db-40e6-9901-27ca629d0477/resource/4a615df8-4c25-48fa-bbea-873a36a79518/download/ralie-usina.csv"
    private val httpClient = HttpClient.newHttpClient()


    @Scheduled(cron = "0 0 3,23 * * *")
    fun downloadAndProcessCsv() {
        logger.info("🔽 Downloading CSV...")

        try {
            val content = downloadCsv(csvUrl)
            processCsv(content)
            logger.info("✅ CSV processed and saved successfully!")
        } catch (e: Exception) {
            logger.error("❌ Error downloading CSV: ${e.message}", e)
        }
    }

    fun downloadCsv(url: String): String {
        val request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .GET()
            .build()

        val response = httpClient.send(request, HttpResponse.BodyHandlers.ofByteArray())

        if (response.statusCode() != 200) {
            throw RuntimeException("Failed to download CSV. HTTP Status Code: ${response.statusCode()}")
        }

        return convertToUtf8(response.body())
    }

    fun processCsv(csvContent: String) {
        val rows = parseCsv(csvContent)

        if (rows.isEmpty()) {
            logger.warn("CSV file is empty")
            return
        }


        val header = rows.first()
        val values = rows.drop(1).toList()
        val columnIndexMap = header.withIndex().associate { it.value to it.index }



        for (line in values) {
            try {
                val codeCEG = line.getOrNull(columnIndexMap["CodCEG"] ?: -1) ?: ""
                val name = line.getOrNull(columnIndexMap["NomEmpreendimento"] ?: -1) ?: ""
                val state = line.getOrNull(columnIndexMap["SigUFPrincipal"] ?: -1) ?: ""
                val energySource = line.getOrNull(columnIndexMap["DscOrigemCombustivel"] ?: -1) ?: ""
                val generationType = line.getOrNull(columnIndexMap["SigTipoGeracao"] ?: -1) ?: ""
                val capacity = convertToDouble(line.getOrNull(columnIndexMap["MdaPotenciaOutorgadaKw"] ?: -1)) ?: 0.0
                val status = line.getOrNull(columnIndexMap["DscSituacaoObra"] ?: -1) ?: ""
                val constructionStart = parseDate(line.getOrNull(columnIndexMap["DatInicioObraRealizado"] ?: -1))
                val commissioningDate = parseDate(line.getOrNull(columnIndexMap["DatComissionamentoUGRealizado"] ?: -1))
                val connectionType = line.getOrNull(columnIndexMap["DscTipoConexao"] ?: -1) ?: ""
                val connectionCompany = line.getOrNull(columnIndexMap["NomEmpresaConexao"] ?: -1) ?: ""
                val connectionVoltage = convertToDouble(line.getOrNull(columnIndexMap["MdaTensaoConexao"] ?: -1))
                val reportDate = parseDate(line.getOrNull(columnIndexMap["DatRalie"] ?: -1))

                if (codeCEG.isBlank()) {
                    logger.warn("⚠️ Skipping line due to missing Código CEG: $line")
                    continue
                }

                if(reportDate == null){
                    logger.warn("⚠️ Skipping line due to missing reportDate: $line")
                    continue
                }



                val existingPlant = repository.findByCodeCEG(codeCEG)


                if (existingPlant == null) {
                    repository.save(
                        PowerPlant(
                            codeCEG = codeCEG,
                            name = name,
                            state = state,
                            energySource = energySource,
                            generationType = generationType,
                            capacity = capacity,
                            status = status,
                            constructionStart = constructionStart,
                            commissioningDate = commissioningDate,
                            connectionType = connectionType,
                            connectionCompany = connectionCompany,
                            connectionVoltage = connectionVoltage,
                            reportDate = reportDate,
                        )
                    )
                    logger.info("✅ Inserted: $codeCEG")
                } else {
                    if (reportDate.isAfter(existingPlant.reportDate)) {
                        val updatedPowerPlant = existingPlant.copy (
                            name = name,
                            state = state,
                            energySource = energySource,
                            generationType = generationType,
                            capacity = capacity,
                            status = status,
                            constructionStart = constructionStart,
                            commissioningDate = commissioningDate,
                            connectionType = connectionType,
                            connectionCompany = connectionCompany,
                            connectionVoltage = connectionVoltage,
                            reportDate = reportDate
                        )
                        repository.save(updatedPowerPlant)
                        logger.info("🔄 Updated: $codeCEG")
                    } else {
                        logger.info("⏩ Skipped (old date): $codeCEG")
                    }
                }


            } catch (e: Exception) {
                logger.warn("⚠️ Error processing line: ${e.message}")
            }
        }

    }

    fun getTop5LargestPlants(): List<PowerPlant> = repository.findTop5ByOrderByCapacityDesc()

    fun getAllPowerPlants(): List<PowerPlant> = repository.findAll()

    private fun parseDate(dateString: String?): LocalDate? {
        return try {
            if (dateString.isNullOrBlank()) null else LocalDate.parse(dateString)
        } catch (e: Exception) {
            null
        }
    }

    private fun parseCsv(csvContent: String): List<Array<String>> {
        val reader = CSVReaderBuilder(StringReader(csvContent))
            .withCSVParser(CSVParserBuilder().withSeparator(';').build())
            .build()

        return reader.readAll().also {
            if (it.isEmpty()) logger.warn("⚠️ CSV file is empty")
        }
    }

}