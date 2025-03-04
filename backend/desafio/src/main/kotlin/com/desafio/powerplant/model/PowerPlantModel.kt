package com.desafio.powerplant.model

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "power_plants")
data class PowerPlant(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false, unique = true)
    val codeCEG: String,

    @Column(nullable = false)
    val name: String,

    @Column(length = 2, nullable = false)
    val state: String,

    @Column(nullable = false)
    val energySource: String,

    @Column(nullable = false)
    val generationType: String,

    @Column(name = "capacity_kw", nullable = false)
    val capacity: Double,

    @Column(nullable = false)
    val status: String,

    @Column(name = "construction_start")
    val constructionStart: LocalDate?,

    @Column(name = "commissioning_date")
    val commissioningDate: LocalDate?,

    @Column(name = "connection_type")
    val connectionType: String?,

    @Column(name = "connection_company")
    val connectionCompany: String?,

    @Column(name = "connection_voltage")
    val connectionVoltage: Double?,

    @Column(name = "report_date", nullable = false)
    val reportDate: LocalDate
)
