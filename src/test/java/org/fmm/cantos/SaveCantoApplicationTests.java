package org.fmm.cantos;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.fmm.cantos.controller.DownloadController;
import org.fmm.cantos.controller.JsonUtils;
import org.fmm.cantos.dto.CantoDTO;
import org.fmm.cantos.model.entity.Canto;
import org.fmm.cantos.service.ProcessDataService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class SaveCantoApplicationTests {
	private static final Logger logger = LoggerFactory.getLogger(SaveCantoApplicationTests.class);

	@Autowired
	private ProcessDataService processDataService;
	
	@Test
	void guardarCanto() {
		CantoDTO cantoDTO = null;
		Canto canto = null;

		cantoDTO = new CantoDTO();
		cantoDTO.setName("Nombre de prueba");
		cantoDTO.setNameShort("nombre_de_prueba");
		cantoDTO.addSummary("Este es un canto de pruebas");
		cantoDTO.addSummary("Hecho por Kiko");
		
//		cantoDTO.addMp3Url("https://url1");
//		cantoDTO.addMp3Url("https://url2");
		
//		cantoDTO.addLabel("blanco");
//		cantoDTO.addLabel("liturgia");
		canto = processDataService.processCanto(cantoDTO);
		
		Assert.notNull(canto, "Algo fue mal");
		Assert.notEmpty(canto.getSummary(), "Algo fue mal");

		cantoDTO = new CantoDTO();
		cantoDTO.setName("Nombre de prueba");
		cantoDTO.setNameShort("nombre_de_prueba");
		cantoDTO.addSummary("Este es un canto de pruebas");
		cantoDTO.addSummary("Hecho por Kiko");
		
//		cantoDTO.addMp3Url("https://url1");
//		cantoDTO.addMp3Url("https://url2");
		
//		cantoDTO.addLabel("blanco");
//		cantoDTO.addLabel("liturgia");
		canto = processDataService.processCanto(cantoDTO);
		
		Assert.notNull(canto, "Algo fue mal");
		Assert.notEmpty(canto.getSummary(), "Algo fue mal");
	}
	
//	@Test
	void leerCantos() {
		List<Canto> listCantos = processDataService.readCantos();
		Assert.notNull(listCantos, "Algo fue mal");
	}

//	@Test
	void procesar() {
		Map<String, CantoDTO> cantos = null;
		String json = null;
		json = JsonUtils.readJson("cantos.json");
		cantos = JsonUtils.toObject(json);
		processDataService.processData(cantos);
		
	}
	
//	@Test
	void procesarCanto() {
		Map<String, CantoDTO> cantos = null;
		String json = null;
		json = JsonUtils.readJson("cantos.json");
		cantos = JsonUtils.toObject(json);

		for (Entry<String, CantoDTO> entryDTO: cantos.entrySet()) {
			processDataService.processCanto(entryDTO.getValue());
			logger.info("Processed: {}", entryDTO.getKey());
		}
		
	}
}
