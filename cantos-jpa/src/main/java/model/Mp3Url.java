package model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;


/**
 * The persistent class for the "mp3Url" database table.
 * 
 */
@Entity
@Table(name="\"mp3Url\"")
@NamedQuery(name="Mp3Url.findAll", query="SELECT m FROM Mp3Url m")
public class Mp3Url implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"id\"")
	private int id;

	@Column(name="\"canto_id\"")
	private int cantoId;

	@Column(name="\"url\"")
	private String url;

	//bi-directional many-to-one association to Canto
	@ManyToOne
	@JoinColumns({
		})
	private Canto canto;

	public Mp3Url() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCantoId() {
		return this.cantoId;
	}

	public void setCantoId(int cantoId) {
		this.cantoId = cantoId;
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