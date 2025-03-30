package org.fmm.cantos.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

import org.fmm.cantos.dto.CantoDTO;

public class ObjectUtils {
	@SuppressWarnings("unchecked")
	public static Map<String, CantoDTO> readObjects(String fileName) {
		ObjectInputStream ois = null;
		Map<String, CantoDTO> cantos = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(fileName));
			cantos = (Map<String, CantoDTO>)ois.readObject();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cantos;
	}

    public static void saveObject (Map<String, CantoDTO> cantos, String fileName) {
    	ObjectOutputStream oos = null;
    	try {
			oos = new ObjectOutputStream(new FileOutputStream(fileName));
			oos.writeObject(cantos);
			oos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
