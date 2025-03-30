package org.fmm.cantos.controller;

import java.security.Principal;
import java.util.Map;

import org.fmm.cantos.dto.CantoDTO;
import org.fmm.cantos.service.ProcessDataService;
import org.fmm.cantos.service.RequestDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/download")
public class DownloadController {
	private static final Logger logger = LoggerFactory.getLogger(DownloadController.class);

	@Autowired
	private RequestDataService requestDataService;
	
	@Autowired
	private ProcessDataService processDataService;
	
    @PostMapping()
//    @PreAuthorize("hasAuthority('me')")
    public ResponseEntity<String> create(Principal principal) {
    	logger.debug("Starting download");
    	Map<String, CantoDTO> cantos = null;
    	cantos = requestDataService.downloadProcess();
        processDataService.processData(cantos);
        return ResponseEntity.ok("Ok");
    }

    @PostMapping(path = "/save", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> onlyDownload(Principal principal) {
    	logger.debug("Starting download");
  	    Map<String, CantoDTO> cantos = null;
  	    String json = null;
  	    
  	    cantos = requestDataService.downloadProcess();
  	    ObjectUtils.saveObject(cantos, "cantos.dat");
  	    json = JsonUtils.toJson(cantos);
  	    JsonUtils.saveJson(json, "cantos.json");
        return ResponseEntity.ok(json);
    }

    @PostMapping(path = "/process", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> onlyProcess(@RequestBody String json) {
    	Map<String, CantoDTO> cantos=null;
    	logger.debug("JSON: {}", json);
    	cantos = JsonUtils.toObject(json);
        processDataService.processData(cantos);
        return ResponseEntity.ok("Ok");
	}
    
}
