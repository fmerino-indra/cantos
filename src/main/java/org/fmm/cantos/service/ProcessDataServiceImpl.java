package org.fmm.cantos.service;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.sql.DataSource;

import org.fmm.cantos.dto.CantoDTO;
import org.fmm.cantos.dto.LabelEnum;
import org.fmm.cantos.model.entity.Canto;
import org.fmm.cantos.model.entity.Mp3Url;
import org.fmm.cantos.model.repository.CantoRepository;
import org.fmm.cantos.model.repository.FileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service("ProcessDataService")
public class ProcessDataServiceImpl implements ProcessDataService {
	private static final Logger logger = LoggerFactory.getLogger(ProcessDataService.class);


	@Autowired
	CantoRepository cantoRepository;
	
	@Autowired
	FileRepository fileRepository;
	
	@Autowired
	LabelUtils labelUtils;
	
//    @PersistenceContext
//    private EntityManager entityManager;

//    @Autowired
//    private DataSource dataSource;
    

	@Override
	public void processData(Map<String, CantoDTO> cantos) {
		for (Entry<String, CantoDTO> entryDTO: cantos.entrySet()) {
			processCanto(entryDTO.getValue());
			logger.info("Processed: {}", entryDTO.getKey());
		}
	}

	@Override
//	@Transactional //(propagation = Propagation.REQUIRES_NEW)
	public Canto processCanto(CantoDTO cantoDTO) {
		String[] aux = null;
		Canto canto = null;
		canto = new Canto();
		canto.setName(cantoDTO.getName());
		canto.setShortName(cantoDTO.getNameShort());
		canto.setRelativeUrl(cantoDTO.getUrl());
		canto.setUrl(cantoDTO.getUrl());
		canto.setImgUrl(cantoDTO.getImgUrl());
//		canto = cantoRepository.save(canto);

		if (cantoDTO.getEnumLabels() != null) {
			for (LabelEnum enumLabel: cantoDTO.getEnumLabels()) {
				logger.debug("Processing label: {}", enumLabel.getLabel());
				canto.addLabel(labelUtils.from(enumLabel.getLabelId()));
			}
		}
		
		Mp3Url auxMp3 = null;
		if (cantoDTO.getMp3Url() != null) {
			for (String mp3Url : cantoDTO.getMp3Url()) {
				auxMp3 = new Mp3Url();
				auxMp3.setUrl(mp3Url);
	//			auxMp3.setCanto(canto);
				canto.addMp3Url(auxMp3);
			}
		}

		if (cantoDTO.getSummary() !=null) {
			aux = new String[cantoDTO.getSummary().size()];
			int i = 0;
			for (String summary : cantoDTO.getSummary()) {
				aux[i++] = summary;
			}
			
			canto.setSummary(aux);
		}
		try {
			canto = cantoRepository.saveAndFlush(canto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		entityManager.detach(canto);
//		dataSource.getClass().getName();
		
		return canto;
	}
	
	@Override
	public List<Canto> readCantos() {
		List<Canto> cantosList = null;
		cantosList = cantoRepository.listCantos();
		return cantosList;
	}
	


}
