package org.fmm.cantos.model.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;


/**
 * The persistent class for the "mp3Url" database table.
 * 
 */
@Entity(name = "Mp3Url")
@Table(name="\"mp3_url\"")
@NamedQuery(name="Mp3Url.findAll", query="SELECT m FROM Mp3Url m")
public class Mp3Url implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="\"id\"")
	private int id;

	@Column(name="\"url\"")
	private String url;

	//bi-directional many-to-one association to Canto
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="canto_id", nullable=false, insertable=true, updatable=true)
	private Canto canto;

	public Mp3Url() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Canto getCanto() {
		return this.canto;
	}

	public void setCanto(Canto canto) {
		this.canto = canto;
	}

}