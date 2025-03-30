package org.fmm.cantos.service;

import java.util.Map;

import org.fmm.cantos.dto.CantoDTO;

public interface RequestDataService {
	Map<String, CantoDTO> downloadProcess();
}
