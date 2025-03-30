package org.fmm.cantos;

import org.fmm.cantos.dto.CantoDTO;
import org.fmm.cantos.model.entity.Canto;
import org.fmm.cantos.model.repository.CantoRepository;
import org.fmm.cantos.service.ProcessDataService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class JPAApplicationTests {
	private static final Logger logger = LoggerFactory.getLogger(JPAApplicationTests.class);

	@Autowired
	CantoRepository cantoRepository;
	
	@Autowired
	private ProcessDataService processDataService;
	
//	@Test
	void guardarCanto() {
		Canto canto = null;
		CantoDTO cantoDTO = new CantoDTO();
		
		
		cantoDTO = new CantoDTO();
		cantoDTO.setName("Nombre de prueba");
		cantoDTO.setNameShort("nombre_de_prueba");
		cantoDTO.addSummary("Este es un canto de pruebas");
		cantoDTO.addSummary("Hecho por Kiko");
		
		canto = new Canto();
		canto.setName(cantoDTO.getName());
		canto.setShortName(cantoDTO.getNameShort());
		canto.setRelativeUrl(cantoDTO.getUrl());
		canto.setUrl(cantoDTO.getUrl());
		canto.setImgUrl(cantoDTO.getImgUrl());
		canto = cantoRepository.save(canto);
		
		Assert.notNull(canto, "Algo fue mal");

		canto = new Canto();
		canto.setName(cantoDTO.getName());
		canto.setShortName(cantoDTO.getNameShort());
		canto.setRelativeUrl(cantoDTO.getUrl());
		canto.setUrl(cantoDTO.getUrl());
		canto.setImgUrl(cantoDTO.getImgUrl());
		canto = cantoRepository.save(canto);
		
		Assert.notNull(canto, "Algo fue mal");

		canto = new Canto();
		canto.setName(cantoDTO.getName());
		canto.setShortName(cantoDTO.getNameShort());
		canto.setRelativeUrl(cantoDTO.getUrl());
		canto.setUrl(cantoDTO.getUrl());
		canto.setImgUrl(cantoDTO.getImgUrl());
		canto = cantoRepository.save(canto);
		
		Assert.notNull(canto, "Algo fue mal");
	}
	
	@Test
	void guardarCantoSrv() {
		CantoDTO cantoDTO = new CantoDTO();
		
		
		cantoDTO = new CantoDTO();
		cantoDTO.setName("Nombre de prueba");
		cantoDTO.setNameShort("nombre_de_prueba");
		cantoDTO.addSummary("Este es un canto de pruebas");
		cantoDTO.addSummary("Hecho por Kiko");
		
		this.processDataService.processCanto(cantoDTO);
		this.processDataService.processCanto(cantoDTO);
		this.processDataService.processCanto(cantoDTO);
		this.processDataService.processCanto(cantoDTO);
		this.processDataService.processCanto(cantoDTO);
		
	}
}
