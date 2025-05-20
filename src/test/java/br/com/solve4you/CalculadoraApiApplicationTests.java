package br.com.solve4you;

import br.com.solve4you.domain.model.Building;
import br.com.solve4you.domain.repository.BuildingRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@SpringBootTest
class CalculadoraApiApplicationTests {


	@Autowired
	private BuildingRepository buildingRepository;

	@Test
	void testFindByIdWithBuildingRequirements() {
		// ID de um Building existente no banco de dados
		int buildingId = 1;

		// Chama o método do repositório
		Optional<Building> building = buildingRepository.findByIdWithBuildingRequirements(buildingId);

		// Verifica se o resultado não é vazio
		assertTrue("Building não encontrado", building.isPresent());

		// Verifica se os requisitos foram carregados
		assertNotNull(building.get().getBuildingRequirements(), "Requisitos não carregados");
	}

	@Test
	void contextLoads() {
	}

}
