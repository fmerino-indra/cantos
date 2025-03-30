package org.fmm.cantos;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.fmm.cantos.controller.JsonUtils;
import org.fmm.cantos.controller.ObjectUtils;
import org.fmm.cantos.dto.CantoDTO;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.Assert;

@SpringBootTest(classes = {DownloadApplication.class})
@AutoConfigureMockMvc
class ControllerApplicationTests {
	private static final Logger logger = LoggerFactory.getLogger(ControllerApplicationTests.class);
	
	@Autowired
    private MockMvc mvc;
	
//	@WithMockUser(username="felix", roles= {"USER", "ADMIN"})
//	@Test
//  Sólo para inicializar datos maestros
	void test1() throws Exception {
	    
		mvc.perform(post("/init").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}

	/**
	 * Realiza todo el proceso: DownloadController.create
	 * - requestDataService.downloadProcess();
	 * - DataService.processData(cantos);
	 * 
	 * @throws Exception
	 */
//	@Test
	void test2() throws Exception {
		mvc.perform(post("/download").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}

//	@Test
	void test3() throws Exception {
		MvcResult result = null;
		String response = null;
		result = mvc.perform(post("/download/save").contentType(MediaType.APPLICATION_JSON)).andReturn();
		response = result.getResponse().getContentAsString();
		logger.debug("Result: {}", response);
		response = new String(result.getResponse().getContentAsByteArray(), StandardCharsets.UTF_8);
		logger.debug("Result: {}", response);
		JsonUtils.saveJson(response, "cantos.json");
//		result.getResponse().
	}
	@Test
	void test4() throws Exception {
		Map<String, CantoDTO> cantos = null;
		String json = null;
		json = JsonUtils.readJson("cantos.json");
		cantos = ObjectUtils.readObjects("cantos.dat");
//		Assert.notNull(cantos, "Algo fue mal");
		cantos = JsonUtils.toObject(json);
		Assert.notNull(cantos, "Algo fue mal");
		
		mvc.perform(post("/download/process").contentType(MediaType.APPLICATION_JSON).content(json))
		.andExpect(status().isOk());
	}
	
	
}
