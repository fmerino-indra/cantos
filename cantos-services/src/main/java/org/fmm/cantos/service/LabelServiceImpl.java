package org.fmm.cantos.service;

import org.fmm.cantos.model.entity.Label;
import org.fmm.cantos.model.repository.LabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("LabelService")
public class LabelServiceImpl implements LabelService {

	@Autowired
	private LabelRepository labelRepository;
	
	@Override
	public Label createLabel(String name) {
		Label label = null;
		label = new Label();
		label.setLabel(name.toLowerCase());
		return labelRepository.save(label);
	}

	@Override
	public Label updateLabel(Label label) {
		// TODO Auto-generated method stub
		return null;
	}

}
