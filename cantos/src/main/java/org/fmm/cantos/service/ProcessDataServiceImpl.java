package org.fmm.cantos.service;

import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.fmm.cantos.dto.CantoDTO;
import org.fmm.cantos.dto.FileTypeEnum;
import org.fmm.cantos.dto.LabelEnum;
import org.fmm.cantos.model.entity.Canto;
import org.fmm.cantos.model.entity.File;
import org.fmm.cantos.model.entity.Mp3Url;
import org.fmm.cantos.model.repository.CantoRepository;
import org.fmm.cantos.model.repository.FileRepository;
import org.fmm.cantos.model.util.FileTypeUtils;
import org.fmm.cantos.model.util.LabelUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ProcessDataService")
public class ProcessDataServiceImpl implements ProcessDataService {
	private static final Logger logger = LoggerFactory.getLogger(ProcessDataService.class);


	@Autowired
	CantoRepository cantoRepository;
	
	@Autowired
	FileRepository fileRepository;
	
	@Autowired
	LabelUtils labelUtils;
	
	@Autowired
	FileTypeUtils fileTypeUtils;
	
	@Override
	public void processData(Map<String, CantoDTO> cantos) {
		for (Entry<String, CantoDTO> entryDTO: cantos.entrySet()) {
			logger.info("---------------------------------------------------------------");
			logger.info("Processing: {}", entryDTO.getKey());
			logger.info("---------------------------------------------------------------");
			processCanto(entryDTO.getValue());
			logger.info("---------------------------------------------------------------");
			logger.info("Processed: {}", entryDTO.getKey());
			logger.info("---------------------------------------------------------------");
		}
	}

	@Override
	public Canto processCanto(CantoDTO cantoDTO) {
		String[] aux = null;
		Canto canto = null;
		File file = null;
		
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
		
		if (cantoDTO.getImgFile() != null) {
			file = new File();
			file.setFileType(fileTypeUtils.from(FileTypeEnum.PNG.getId()));
			file.setContent(readBinary(cantoDTO.getImgFile()));
			canto.addFile(file);
		}
		
		if (cantoDTO.getMp3Files() != null) {
			for (Path mp3File : cantoDTO.getMp3Files()) {
				file = new File();
				file.setFileType(fileTypeUtils.from(FileTypeEnum.MP3.getId()));
				file.setContent(readBinary(mp3File));
				canto.addFile(file);
			}
		}

		try {
			canto = cantoRepository.saveAndFlush(canto);
		} catch (Exception e) {
			logger.error("ERROR Processing {}", canto.getShortName());
			e.printStackTrace();
		}
		
		return canto;
	}
	
	@Override
	public List<Canto> readCantos() {
		List<Canto> cantosList = null;
		cantosList = cantoRepository.listCantos();
		return cantosList;
	}
	
	private byte[] readBinary(Path newFile) {
		byte[] fichero = null;
		ByteBuffer bb = null;
		
		FileChannel channel = null;

		try {
			channel = FileChannel.open(newFile,StandardOpenOption.READ);
			//Primero hay que reservar memoria para fichero
			bb = ByteBuffer.allocate((int)channel.size()); 
			channel.read(bb);
			fichero = bb.array();
			channel.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fichero;
	}



}
