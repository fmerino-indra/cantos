package org.fmm.cantos.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import org.fmm.cantos.dto.CantoDTO;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;

public class JsonUtils {

	public static void saveJson(String response, String fileName) {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(fileName));
			writer.write(response);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    public static String toJson(Map<String, CantoDTO> cantos) {
    	ObjectMapper om = null;
    	ObjectWriter ow = null;
    	String json = null;
    	om = new ObjectMapper();
    	ow = om.writer().withDefaultPrettyPrinter();
    	try {
			json = ow.writeValueAsString(cantos);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
    	return json;
    }
    
    
    public static Map<String, CantoDTO> toObject(String json) {
    	ObjectMapper om = null;
    	ObjectReader or = null;
    	Map<String, CantoDTO> cantos = null;
    	
    	TypeReference<Map<String,CantoDTO>> typRef = null;

    	om = new ObjectMapper();
    	or = om.reader();

    	typRef = new TypeReference<Map<String, CantoDTO>>() {};
    	
    	JavaType javaType = om.getTypeFactory().constructParametricType(Map.class, String.class, CantoDTO.class);
    	try {
//			or.readValue(json, typRef.getClass());
    		
			cantos = om.readValue(json, javaType);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return cantos;
    }
    public static String readJson(String fileName) {
    	BufferedReader br = null;
    	StringBuffer sb = null;
    	try {
			br = new BufferedReader(new FileReader(fileName));
			sb = new StringBuffer();
			while (br.ready()) {
				sb.append(br.readLine());
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return sb.toString();
    }
}
