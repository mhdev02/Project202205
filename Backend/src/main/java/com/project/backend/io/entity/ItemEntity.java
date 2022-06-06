package com.project.backend.io.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "items")
public class ItemEntity implements Serializable {

	private static final long serialVersionUID = 6664315447659391969L;

	@Id
	@GeneratedValue
	private long id;

	@Column(length = 30, nullable = false)
	private String itemId;

	@Column(length = 20, nullable = false)
	private String name;

	@Column(nullable = false)
	private Integer price;

	@Column(nullable = false)
	private Integer stock;

	@ManyToOne
	@JoinColumn(name = "users_id")
	private UserEntity userSelling;

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

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public UserEntity getUserPurchases() {
		return userPurchases;
	}

	public void setUserPurchases(UserEntity userPurchases) {
		this.userPurchases = userPurchases;
	}

}