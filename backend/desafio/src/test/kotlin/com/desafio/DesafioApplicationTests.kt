package com.desafio

import com.desafio.powerplant.repository.PowerPlantRepository
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
class DesafioApplicationTests {

	@MockK
	private lateinit var powerPlantRepository: PowerPlantRepository

	@Test
	fun contextLoads() {
	}
}
