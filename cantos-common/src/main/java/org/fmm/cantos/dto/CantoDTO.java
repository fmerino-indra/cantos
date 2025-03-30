package org.fmm.cantos.dto;

import java.io.Serializable;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CantoDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(CantoDTO.class);
	
	private String name;
	private String url;
	private String nameShort;
	private List<String> mp3Url;
	private String imgUrl;
	private List<String> labels;
	private List<LabelEnum> enumLabels;
	private List<String> summary;
	private byte[] mp3;
	private byte[] img;
	private Path imgFile;
	private List<Path> mp3Files;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getNameShort() {
		return nameShort;
	}
	public void setNameShort(String nameShort) {
		this.nameShort = nameShort;
	}
	public List<String> getMp3Url() {
		return mp3Url;
	}
	public void addMp3Url(String mp3Url) {
		if (this.mp3Url == null)
			this.mp3Url = new ArrayList<String>();
		this.mp3Url.add(mp3Url);
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public void addLabel(String label) {
		if (labels == null)
			labels = new ArrayList<>();
		if (enumLabels == null)
			enumLabels = new ArrayList<>();
		
		labels.add(label);
		if (LabelEnum.from(label) != null)
			enumLabels.add(LabelEnum.from(label));
		else {
			logger.warn("[{}]¡¡¡¡Etiqueta: {} no existe como enum!!!!", nameShort,label);
		}
			
	}
	
	public void addSummary(String summary) {
		if (this.summary == null)
			this.summary = new ArrayList<>();
		this.summary.add(summary);
	}
	public byte[] getMp3() {
		return mp3;
	}
	public void setMp3(byte[] mp3) {
		this.mp3 = mp3;
	}
	public byte[] getImg() {
		return img;
	}
	public void setImg(byte[] img) {
		this.img = img;
	}
	public List<String> getLabels() {
		return labels;
	}
	public List<String> getSummary() {
		return summary;
	}
	public List<LabelEnum> getEnumLabels() {
		return enumLabels;
	}
	public Path getImgFile() {
		return imgFile;
	}
	public void setImgFile(Path imgFile) {
		this.imgFile = imgFile;
	}
	
	public void addMp3File(Path mp3File) {
		if (mp3Files == null)
			mp3Files = new ArrayList<>();
		mp3Files.add(mp3File);
	}
	public List<Path> getMp3Files() {
		return mp3Files;
	}
	public void setMp3Files(List<Path> mp3Files) {
		this.mp3Files = mp3Files;
	}
}
