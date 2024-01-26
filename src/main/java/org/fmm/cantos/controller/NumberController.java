package org.fmm.cantos.controller;

import java.text.NumberFormat;
import java.util.Locale;

import org.fmm.cantos.dto.NumbersDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/numbers")
public class NumberController {
	private static final Logger logger = LoggerFactory.getLogger(NumberController.class);

//    @PostMapping()
//    public ResponseEntity<String> numbersText(@RequestParam String amountIn, @RequestParam String currencyIn) {
    public ResponseEntity<String> numbersText(@RequestBody String numbers) {
    	logger.debug("Init numbers");
    	Locale defaultLocale = null;
    	NumberFormat amountFormat = null;
    	NumberFormat currencyFormat = null;
    	
    	Double amount = null;
    	Double currency = null;
    	
    	String amountOut = null;
    	String currencyOut = null;
    	
    	defaultLocale = Locale.getDefault();
    	currencyFormat = NumberFormat.getNumberInstance();
    	currencyFormat = NumberFormat.getCurrencyInstance();
    	
    	
    	logger.debug("End numbers");
    	
        return ResponseEntity.ok("Ok. Hello world");
    }

    @PostMapping()
  public ResponseEntity<String> numbersTextOld(@RequestBody NumbersDTO numbers) {
  	logger.debug("Init numbers");
  	Locale defaultLocale = null;
  	NumberFormat amountFormat = null;
  	NumberFormat currencyFormat = null;
  	
  	Double amount = null;
  	Double currency = null;
  	
  	String amountOut = null;
  	String currencyOut = null;
  	
  	amount = Double.valueOf(numbers.getAmount());
  	currency = Double.valueOf(numbers.getMoney());
  	
  	defaultLocale = Locale.getDefault();
  	currencyFormat = NumberFormat.getNumberInstance();
  	currencyFormat = NumberFormat.getCurrencyInstance();
  	
  	
  	logger.debug("End numbers");
  	
      return ResponseEntity.ok("Ok");
  }

}
