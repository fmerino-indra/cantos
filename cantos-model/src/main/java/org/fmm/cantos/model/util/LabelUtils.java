package org.fmm.cantos.model.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fmm.cantos.model.entity.Label;
import org.fmm.cantos.model.repository.LabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class LabelUtils {
	
	@Autowired
	LabelRepository labelRepository;
	
	private List<Label> labelList;
	
    private Map<String, Label> labelMap;
    private Map<Integer, Label> idMap;

    @PostConstruct
	public void init() {
		labelList = labelRepository.findAll();
		initMaps();
	}
	
    private void initMaps() {
    	labelMap = new HashMap<>();
    	idMap = new HashMap<>();
    	for (Label label: labelList) {
    		labelMap.put(label.getLabel(), label);
    		idMap.put(label.getLabelId(), label);
    	}
    }

    public Label from(String aux) {
    	return labelMap.get(aux);
    }
    public Label from(Integer aux) {
    	return idMap.get(aux);
    }
}
