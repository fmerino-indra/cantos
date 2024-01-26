package org.fmm.cantos.service;

import static org.fmm.cantos.dto.FileTypeEnum.MP3;
import static org.fmm.cantos.dto.FileTypeEnum.PNG;

import static org.fmm.cantos.dto.LabelEnum.ACLAMACION;
import static org.fmm.cantos.dto.LabelEnum.ADVIENTO;
import static org.fmm.cantos.dto.LabelEnum.AZUL;
import static org.fmm.cantos.dto.LabelEnum.BLANCO;
import static org.fmm.cantos.dto.LabelEnum.CATECUMENADO;
import static org.fmm.cantos.dto.LabelEnum.COMUNION;
import static org.fmm.cantos.dto.LabelEnum.ELECCION;
import static org.fmm.cantos.dto.LabelEnum.ENTRADA;
import static org.fmm.cantos.dto.LabelEnum.FINAL;
import static org.fmm.cantos.dto.LabelEnum.FRACCIONDELPAN;
import static org.fmm.cantos.dto.LabelEnum.INDICE;
import static org.fmm.cantos.dto.LabelEnum.LAUDES;
import static org.fmm.cantos.dto.LabelEnum.LITURGIA;
import static org.fmm.cantos.dto.LabelEnum.NAVIDAD;
import static org.fmm.cantos.dto.LabelEnum.PASCUA;
import static org.fmm.cantos.dto.LabelEnum.PAZ;
import static org.fmm.cantos.dto.LabelEnum.PENITENCIAL;
import static org.fmm.cantos.dto.LabelEnum.PRECATECUMENADO;
import static org.fmm.cantos.dto.LabelEnum.SALMODIA;
import static org.fmm.cantos.dto.LabelEnum.VERDE;
import static org.fmm.cantos.dto.LabelEnum.VIRGEN;

import org.fmm.cantos.dto.LabelEnum;
import org.fmm.cantos.model.entity.FileType;
import org.fmm.cantos.model.entity.Label;
import org.fmm.cantos.model.repository.FileTypeRepository;
import org.fmm.cantos.model.repository.LabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("InitDBService")
public class InitDBServiceImpl implements InitDBService {

	@Autowired
	private LabelRepository labelRepository;
	
	@Autowired
	private FileTypeRepository fileTypeRepository;
	
	@Override
	public void initDB() {
		initFileType();
		initLabels();
	}

	private void initLabels() {
		initLabel(BLANCO);
		initLabel(AZUL);
		initLabel(VERDE);
		initLabel(LITURGIA);
		initLabel(ACLAMACION);
		initLabel(ADVIENTO);
		initLabel(COMUNION);
		initLabel(ELECCION);
		initLabel(ENTRADA);
		initLabel(FINAL);
		initLabel(FRACCIONDELPAN);
		initLabel(LAUDES);
		initLabel(NAVIDAD);
		initLabel(PASCUA);
		initLabel(PAZ);
		initLabel(PENITENCIAL);
		initLabel(PRECATECUMENADO);
		initLabel(SALMODIA);
		initLabel(VIRGEN);
		initLabel(CATECUMENADO);
		initLabel(INDICE);
	}

	private void initLabel(LabelEnum labelEnum) {
		Label label = null;
		
		label = new Label();
		label.setLabelId(labelEnum.getLabelId());
		label.setLabel(labelEnum.getLabel());
		labelRepository.save(label);	
	}

	private void initFileType() {
		FileType fileType = null;
		
		fileType = new FileType();
		fileType.setFileTypeId(MP3.getId());
		fileType.setTypeName(MP3.getName());
		fileTypeRepository.save(fileType);
		
		fileType = new FileType();
		fileType.setFileTypeId(PNG.getId());
		fileType.setTypeName(PNG.getName());
		fileTypeRepository.save(fileType);
	}
}
