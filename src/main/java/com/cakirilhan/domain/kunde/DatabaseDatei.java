package com.cakirilhan.domain.kunde;

import java.time.LocalDate;
import java.util.Arrays;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;


@Entity

public class DatabaseDatei {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String fileName;
	
	private String fileType;
	
	@Lob
	private byte[] data;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate datum;
	
	@ManyToOne
	private Vertrag vertrag;
	
	

	
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public LocalDate getDatum() {
		return datum;
	}

	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}

	

	public Vertrag getVertrag() {
		return vertrag;
	}

	public void setVertrag(Vertrag vertrag) {
		this.vertrag = vertrag;
	}

	public DatabaseDatei(String fileName, String fileType, byte[] data,
			LocalDate datum, Vertrag vertrag) {
		this.fileName = fileName;
		this.fileType = fileType;
		this.data = data;
		this.datum = datum;
		this.vertrag = vertrag;
	}

	public DatabaseDatei() {
		
	}

	@Override
	public String toString() {
		return "DatabaseDatei [id=" + id + ", fileName=" + fileName
				+ ", fileType=" + fileType + ", data=" + Arrays.toString(data)
				+ ", datum=" + datum + ", vertrag=" + vertrag + "]";
	}

	
	
	
	

}
