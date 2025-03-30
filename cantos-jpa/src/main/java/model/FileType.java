package model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


/**
 * The persistent class for the "file_type" database table.
 * 
 */
@Entity
@Table(name="\"file_type\"")
@NamedQuery(name="FileType.findAll", query="SELECT f FROM FileType f")
public class FileType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"file_type_id\"")
	private int fileTypeId;

	@Column(name="\"type_name\"")
	private String typeName;

	//bi-directional many-to-one association to File
	@OneToMany(mappedBy="fileType")
	private List<File> files;

	public FileType() {
	}

	public int getFileTypeId() {
		return this.fileTypeId;
	}

	public void setFileTypeId(int fileTypeId) {
		this.fileTypeId = fileTypeId;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public List<File> getFiles() {
		return this.files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}

	public File addFile(File file) {
		getFiles().add(file);
		file.setFileType(this);

		return file;
	}

	public File removeFile(File file) {
		getFiles().remove(file);
		file.setFileType(null);

		return file;
	}

}