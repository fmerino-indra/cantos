package org.fmm.cantos.controller;

import java.security.Principal;

import org.fmm.cantos.model.entity.Label;
import org.fmm.cantos.service.LabelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/label")
public class LabelController {
	private static final Logger logger = LoggerFactory.getLogger(LabelController.class);

	private LabelService labelService;
    @PostMapping()
//    @PreAuthorize("hasAuthority('me')")
    public ResponseEntity<Label> create(@RequestBody String label, Principal principal) {
    	logger.debug("Creating the label: {}", label);
        Label dto = null;
        dto = labelService.createLabel(label);
        
        return ResponseEntity.ok(dto);
    }

	
}
