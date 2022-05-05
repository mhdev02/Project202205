package com.project.backend.shared.dto;

public class ItemDto {

	private long id;
	private String itemId;
	private String name;
	private Integer price;
	private Integer stock;
	private UserDto userPurchases;

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

	public UserDto getUserPurchases() {
		return userPurchases;
	}

	public void setUserPurchases(UserDto userPurchases) {
		this.userPurchases = userPurchases;
	}
}