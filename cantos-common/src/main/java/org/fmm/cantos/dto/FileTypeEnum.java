package org.fmm.cantos.dto;

public enum FileTypeEnum {
	MP3(1, "MP3"),
	PNG(2, "PNG");
    
    FileTypeEnum(int id, String name) {
        this.typeId = id;
        this.typeFileName = name;
    }
    
    private int typeId;
    private String typeFileName;
    
    public int getId() {
        return typeId;
    }
    public void setId(int id) {
        this.typeId = id;
    }
    public String getName() {
        return typeFileName;
    }
    public void setName(String name) {
        this.typeFileName = name;
    }
}
