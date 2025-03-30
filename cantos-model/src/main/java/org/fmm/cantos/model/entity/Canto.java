package org.fmm.cantos.model.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
/**
 * The persistent class for the "canto" database table.
 * 
 */
@Entity
@Table(name="\"canto\"")
@NamedQuery(name="Canto.findAll", query="SELECT c FROM Canto c")
public class Canto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="\"canto_id\"")
	private int cantoId;

	@Column(name="\"img_url\"")
	private String imgUrl;

//	@Column(name="\"mp3_url\"")
//	private String[] mp3Url;
//
	@Column(name="\"name\"")
	private String name;

	@Column(name="\"relative_url\"")
	private String relativeUrl;

	@Column(name="\"short_name\"")
	private String shortName;

	@Column(
			name="summary",
			columnDefinition="summary[]"
	)
	private String[] summary;

	@Column(name="\"url\"")
	private String url;

	//bi-directional many-to-many association to Label
	@ManyToMany
	@JoinTable(
		name="\"canto_label\"",
		joinColumns = @JoinColumn (name = "canto_id"),
		inverseJoinColumns = @JoinColumn (name = "label_id")
	)
	private Set<Label> labels;

	//bi-directional many-to-one association to Mp3Url
//	@OneToMany(mappedBy="canto")
	@OneToMany(targetEntity = Mp3Url.class,cascade=CascadeType.ALL,orphanRemoval = true)
	@JoinColumn(name = "canto_id",nullable=false, insertable=false, updatable=false)
	private Set<Mp3Url> mp3Urls;

	@OneToMany(targetEntity = File.class,cascade=CascadeType.ALL,orphanRemoval = true)
	@JoinColumn(name = "canto_id",nullable=false, insertable=false, updatable=false)
	private Set<File> files;
	
	public Canto() {
	}

	public int getCantoId() {
		return this.cantoId;
	}

	public void setCantoId(int cantoId) {
		this.cantoId = cantoId;
	}

	public String getImgUrl() {
		return this.imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRelativeUrl() {
		return this.relativeUrl;
	}

	public void setRelativeUrl(String relativeUrl) {
		this.relativeUrl = relativeUrl;
	}

	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String[] getSummary() {
		return this.summary;
	}

	public void setSummary(String[] summary) {
		this.summary = summary;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Set<Label> getLabels() {
		return this.labels;
	}

	public void setLabels(Set<Label> labels) {
		this.labels = labels;
	}

	public Set<Mp3Url> getMp3Urls() {
		return this.mp3Urls;
	}

	public void setMp3Urls(Set<Mp3Url> mp3urls) {
		this.mp3Urls = mp3urls;
	}

	public void addLabel(Label label) {
		if (this.labels == null)
			this.labels = new HashSet<Label>();
		this.labels.add(label);
	}
	
	public void addMp3Url(Mp3Url mp3url) {
		if (this.mp3Urls == null)
			this.mp3Urls = new HashSet<Mp3Url>();
		
		getMp3Urls().add(mp3url);
		mp3url.setCanto(this);

//		return mp3url;
	}

	public void removeMp3Url(Mp3Url mp3url) {
		getMp3Urls().remove(mp3url);
		mp3url.setCanto(null);

//		return mp3url;
	}

	public Set<File> getFiles() {
		return files;
	}
	
	public void setFiles(Set<File> files) {
		this.files = files;
	}
	public void addFile(File file) {
		if (this.files == null)
			this.files = new HashSet<File>();
		
		getFiles().add(file);
		file.setCanto(this);
	}
	
	public void removeFile(File file) {
		getFiles().remove(file);
		file.setCanto(null);
	}
}