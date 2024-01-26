package org.fmm.cantos;

import java.text.NumberFormat;
import java.util.Locale;

import org.fmm.cantos.dto.NumbersDTO;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(classes = {DownloadApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
//@AutoConfigureMockMvc
class NumbersControllerTests {
	private static final Logger logger = LoggerFactory.getLogger(NumbersControllerTests.class);
	
//	@Autowired
//    private MockMvc mvc;
	
//	@Autowired
//	private WebTestClient webTestClient;
//	@Autowired
//	private TestRestTemplate testRestTemplate;
	
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private ServletWebServerApplicationContext webServerAppCtxt;

	@Test
	void test1() throws Exception {
    	logger.debug("Init numbers test");
    	
    	int port = webServerAppCtxt.getWebServer().getPort();
    	logger.debug("Port: {}", port);
    	
    	Locale defaultLocale = null;
    	NumberFormat amountFormat = null;
    	NumberFormat currencyFormat = null;
    	
    	Double amount = null;
    	Double currency = null;
    	
    	String amountOut = null;
    	String currencyOut = null;
    	
    	ResponseEntity<String> response = null;
    	HttpEntity<NumbersDTO> request = null;
    	
    	NumbersDTO dto = null;
    	
    	amount = Double.valueOf(1000.01);
    	currency = Double.valueOf(5.3);
    	
    	defaultLocale = Locale.getDefault();
    	amountFormat = NumberFormat.getNumberInstance();
    	currencyFormat = NumberFormat.getCurrencyInstance();
    	
    	dto = new NumbersDTO();
    	dto.setAmount(amount);
    	dto.setMoney(currency);
    	
		MvcResult result = null;
//		result = mvc.perform(post("/dates").contentType(MediaType.APPLICATION_JSON).param("amountIn", amount.toString()).param("currencyIn", currency.toString())).andReturn();
//		result = mvc.perform(post("/dates").param("amountIn", amount.toString()).param("currencyIn", currency.toString())).andReturn();
		
	    HttpHeaders requestHeaders = new HttpHeaders();
	    requestHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
	    
	    request = new HttpEntity<>(dto);
	     
		//HttpEntity<?> httpEntity = new HttpEntity<>();
		
//	    response = restTemplate.postForEntity("http://localhost:8080/numbers", "Tu puta madre", String.class);
	    response = restTemplate.postForEntity("http://localhost:8080/numbers", request, String.class);
	    
		logger.debug (response.getBody());
		
	}

	
}
