package org.fmm.cantos.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public enum LabelEnum implements Serializable {
	BLANCO(1, "blanco"),
	AZUL(2, "azul"),
	VERDE(3, "verde"),
	LITURGIA(4, "liturgia"),
	
	ACLAMACION(5, "aclamacion"),
	ADVIENTO(6, "adviento"),
	COMUNION(7, "comunion"),
	ELECCION(8, "eleccion"),
	ENTRADA(9, "entrada"),
	FINAL(10, "final"),
	
	FRACCIONDELPAN(11, "fracciondelpan"),
	LAUDES(12, "laudes"),
	NAVIDAD(13, "navidad"),
	PASCUA(14, "pascua"),
	PAZ(15, "paz"),
	PENITENCIAL(16, "penitencial"),
	PRECATECUMENADO(17, "precatecumenado"),
	SALMODIA(18, "salmodia"),
	VIRGEN(19, "virgen"),
	
	INDICE(20, "indice");
	
    LabelEnum(int id, String name) {
        this.labelId = id;
        this.label = name;
    }
    
    private int labelId;
    private String label;
    
    private static Map<String, LabelEnum> labelMap;
    private static Map<Integer, LabelEnum> idMap;
    static {
    	labelMap = new HashMap<>();
    	labelMap.put(BLANCO.label, BLANCO);
    	labelMap.put(AZUL.label, AZUL);
    	labelMap.put(VERDE.label, VERDE);
    	labelMap.put(LITURGIA.label, LITURGIA);

    	labelMap.put(ACLAMACION.label, ACLAMACION);
    	labelMap.put(ADVIENTO.label, ADVIENTO);
    	labelMap.put(COMUNION.label, COMUNION);
    	labelMap.put(ELECCION.label, ELECCION);
    	labelMap.put(ENTRADA.label, ENTRADA);
    	labelMap.put(FINAL.label, FINAL);
    	
    	labelMap.put(FRACCIONDELPAN.label, FRACCIONDELPAN);
    	labelMap.put(LAUDES.label, LAUDES);
    	labelMap.put(NAVIDAD.label, NAVIDAD);
    	labelMap.put(PASCUA.label, PASCUA);
    	labelMap.put(PAZ.label, PAZ);
    	labelMap.put(PENITENCIAL.label, PENITENCIAL);
    	labelMap.put(PRECATECUMENADO.label, PRECATECUMENADO);
    	labelMap.put(SALMODIA.label, SALMODIA);
    	labelMap.put(VIRGEN.label, VIRGEN);
    	labelMap.put(INDICE.label, INDICE);
    	
    	idMap = new HashMap<>();
    	idMap.put(BLANCO.labelId, BLANCO);
    	idMap.put(AZUL.labelId, AZUL);
    	idMap.put(VERDE.labelId, VERDE);
    	idMap.put(LITURGIA.labelId, LITURGIA);

    	idMap.put(ACLAMACION.labelId, ACLAMACION);
    	idMap.put(ADVIENTO.labelId, ADVIENTO);
    	idMap.put(COMUNION.labelId, COMUNION);
    	idMap.put(ELECCION.labelId, ELECCION);
    	idMap.put(ENTRADA.labelId, ENTRADA);
    	idMap.put(FINAL.labelId, FINAL);
    	
    	idMap.put(FRACCIONDELPAN.labelId, FRACCIONDELPAN);
    	idMap.put(LAUDES.labelId, LAUDES);
    	idMap.put(NAVIDAD.labelId, NAVIDAD);
    	idMap.put(PASCUA.labelId, PASCUA);
    	idMap.put(PAZ.labelId, PAZ);
    	idMap.put(PENITENCIAL.labelId, PENITENCIAL);
    	idMap.put(PRECATECUMENADO.labelId, PRECATECUMENADO);
    	idMap.put(SALMODIA.labelId, SALMODIA);
    	idMap.put(VIRGEN.labelId, VIRGEN);
    	
    	idMap.put(INDICE.labelId, INDICE);
    }
	public int getLabelId() {
		return labelId;
	}
	public void setLabelId(int labelId) {
		this.labelId = labelId;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
    public static LabelEnum from(String aux) {
    	return labelMap.get(aux);
    }
    public static LabelEnum from(Integer aux) {
    	return idMap.get(aux);
    }
}
