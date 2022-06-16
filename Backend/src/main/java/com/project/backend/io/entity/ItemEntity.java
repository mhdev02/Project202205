package com.project.backend.io.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "items")
public class ItemEntity implements Serializable {

	private static final long serialVersionUID = 6664315447659391969L;

	@Id
	@GeneratedValue
	private long id;

	@NotBlank
	@Column(length = 30)
	private String itemId;
	
	@NotBlank
	@Column(length = 20)
	private String name;

	@NotBlank
	@Column()
	private String price;

	@NotBlank
	@Column()
	private String stock;
	
	@Column(length = 1000)
	private String description;

	@ManyToOne
	@JoinColumn(name = "users_id")
	private UserEntity seller;
	
	@OneToOne 
	@JoinColumn(name = "images_id")
	private ImageEntity image;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public UserEntity getSeller() {
		return seller;
	}

	public void setSeller(UserEntity seller) {
		this.seller = seller;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public ImageEntity getImage() {
		return image;
	}

	public void setImage(ImageEntity image) {
		this.image = image;
	}

}