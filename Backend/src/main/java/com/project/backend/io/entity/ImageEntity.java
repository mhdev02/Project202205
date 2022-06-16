package com.project.backend.io.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "images")
public class ImageEntity implements Serializable {

	private static final long serialVersionUID = 4064246653875246789L;
	
	@Id
	@GeneratedValue
	private long id;
	
	@NotBlank
	@Column(length = 30)
	private String imageId;

	@NotBlank
	@Column(length = 100)
	private String originalName;
	
	@NotBlank
	@Column(length = 100)
	private String mimeType;
	
	@NotNull
	@Lob
	private String data;
	
	@OneToOne(mappedBy = "image")
	private ItemEntity item;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public ItemEntity getItem() {
		return item;
	}

	public void setItem(ItemEntity item) {
		this.item = item;
	}
	
}
