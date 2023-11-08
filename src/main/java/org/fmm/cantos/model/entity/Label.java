package org.fmm.cantos.model.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;


/**
 * The persistent class for the "label" database table.
 * 
 */
@Entity
@Table(name="\"label\"")
@NamedQuery(name="Label.findAll", query="SELECT l FROM Label l")
public class Label implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"label_id\"")
	private int labelId;

	@Column(name="\"label\"")
	private String label;

	//bi-directional many-to-many association to Canto
//	@ManyToMany(mappedBy="labels")
//	private List<Canto> cantos;

	public Label() {
	}

	public int getLabelId() {
		return this.labelId;
	}

	public void setLabelId(int labelId) {
		this.labelId = labelId;
	}

	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

//	public List<Canto> getCantos() {
//		return this.cantos;
//	}
//
//	public void setCantos(List<Canto> cantos) {
//		this.cantos = cantos;
//	}
}