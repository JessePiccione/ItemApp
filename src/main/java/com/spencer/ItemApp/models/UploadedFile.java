package com.spencer.ItemApp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity 
public class UploadedFile {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String fileName;
	private String contentType;
	@Lob
	private byte[] data;
	
	public long getId() {
		return id;
	}
	public String getFileName() {
		return fileName;
	}
	public String getContentType() {
		return contentType;
	}
	public byte[] getData() {
		return data;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
}
