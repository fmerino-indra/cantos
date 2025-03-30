package model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
	@Column(name="\"canto_id\"")
	private int cantoId;

	@Column(name="\"imgUrl\"")
	private String imgUrl;

	@Column(name="\"mp3Url\"")
	private String mp3Url;

	@Column(name="\"name\"")
	private String name;

	@Column(name="\"relative_url\"")
	private String relativeUrl;

	@Column(name="\"short_name\"")
	private String shortName;

	@Column(name="\"summary\"")
	private Object summary;

	@Column(name="\"url\"")
	private String url;

	//bi-directional many-to-many association to Label
	@ManyToMany
	@JoinTable(
		name="\"canto_label\""
		, joinColumns={

			}
		, inverseJoinColumns={

			}
		)
	private List<Label> labels;

	//bi-directional many-to-one association to Mp3Url
	@OneToMany(mappedBy="canto")
	private List<Mp3Url> mp3urls;

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

	public String getMp3Url() {
		return this.mp3Url;
	}

	public void setMp3Url(String mp3Url) {
		this.mp3Url = mp3Url;
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

	public Object getSummary() {
		return this.summary;
	}

	public void setSummary(Object summary) {
		this.summary = summary;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Label> getLabels() {
		return this.labels;
	}

	public void setLabels(List<Label> labels) {
		this.labels = labels;
	}

	public List<Mp3Url> getMp3urls() {
		return this.mp3urls;
	}

	public void setMp3urls(List<Mp3Url> mp3urls) {
		this.mp3urls = mp3urls;
	}

	public Mp3Url addMp3url(Mp3Url mp3url) {
		getMp3urls().add(mp3url);
		mp3url.setCanto(this);

		return mp3url;
	}

	public Mp3Url removeMp3url(Mp3Url mp3url) {
		getMp3urls().remove(mp3url);
		mp3url.setCanto(null);

		return mp3url;
	}

}