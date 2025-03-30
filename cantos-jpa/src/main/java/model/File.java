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
 * The persistent class for the "file" database table.
 * 
 */
@Entity
@Table(name="\"file\"")
@NamedQuery(name="File.findAll", query="SELECT f FROM File f")
public class File implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"file_id\"")
	private int fileId;

	@Column(name="\"content\"")
	private byte[] content;

	@Column(name="\"file_type_id\"")
	private int fileTypeId;

	@Column(name="\"name\"")
	private String name;

	//bi-directional many-to-one association to FileType
	@ManyToOne
	@JoinColumns({
		})
	private FileType fileType;

	public File() {
	}

	public int getFileId() {
		return this.fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	public byte[] getContent() {
		return this.content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public int getFileTypeId() {
		return this.fileTypeId;
	}

	public void setFileTypeId(int fileTypeId) {
		this.fileTypeId = fileTypeId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FileType getFileType() {
		return this.fileType;
	}

	public void setFileType(FileType fileType) {
		this.fileType = fileType;
	}

}