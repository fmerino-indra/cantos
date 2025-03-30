package org.fmm.cantos.model.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fmm.cantos.model.entity.FileType;
import org.fmm.cantos.model.repository.FileTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class FileTypeUtils {
	
	@Autowired
	FileTypeRepository fileTypeRepository;
	
	private List<FileType> fileTypeList;
	
    private Map<String, FileType> fileTypeMap;
    private Map<Integer, FileType> idMap;

    @PostConstruct
	public void init() {
		fileTypeList = fileTypeRepository.findAll();
		initMaps();
	}
	
    private void initMaps() {
    	fileTypeMap = new HashMap<>();
    	idMap = new HashMap<>();
    	for (FileType file: fileTypeList) {
    		fileTypeMap.put(file.getTypeName(), file);
    		idMap.put(file.getFileTypeId(), file);
    	}
    }

    public FileType from(String aux) {
    	return fileTypeMap.get(aux);
    }
    public FileType from(Integer aux) {
    	return idMap.get(aux);
    }
}
