package org.fmm.cantos.initdb;

import java.security.Principal;

import org.fmm.cantos.service.InitDBService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/init")
public class InitDBController {
	private static final Logger logger = LoggerFactory.getLogger(InitDBController.class);

	@Autowired
	private InitDBService initDBService;
	
    @PostMapping()
//    @PreAuthorize("hasAuthority('me')")
    public ResponseEntity<String> create(Principal principal) {
    	logger.debug("Starting DB initialization");
    	initDBService.initDB();
    	return ResponseEntity.ok("Ok");
    }
	
}
