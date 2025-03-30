package org.fmm.cantos.service;

import org.fmm.cantos.model.entity.Label;

public interface LabelService {
	Label createLabel(String name);
	Label updateLabel(Label label);
	
}
