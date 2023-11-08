package org.fmm.cantos.service;

import java.util.List;
import java.util.Map;

import org.fmm.cantos.dto.CantoDTO;
import org.fmm.cantos.model.entity.Canto;

public interface ProcessDataService {
	void processData(Map<String, CantoDTO> cantos);

	Canto processCanto(CantoDTO cantoDTO);

	List<Canto> readCantos();
}
