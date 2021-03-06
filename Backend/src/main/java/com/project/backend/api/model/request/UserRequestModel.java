package com.project.backend.api.model.request;

import java.util.List;

public class UserRequestModel {

	private String firstName;
	private String lastName;
	private String email;
	private String nickName;
	private String password;
	private List<ItemRequestModel> items;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<ItemRequestModel> getItems() {
		return items;
	}

	public void setItems(List<ItemRequestModel> items) {
		this.items = items;
	}
	
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

}