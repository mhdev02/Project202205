package com.project.backend.io.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class UserEntity implements Serializable {

	private static final long serialVersionUID = 5167896616374018174L;

	@Id
	@GeneratedValue
	private long id;

	@NotBlank
	@Column()
	private String userId;

	@NotBlank
	@Column(length = 20)
	private String firstName;

	@NotBlank
	@Column(length = 20)
	private String lastName;
	
	@Email
	@NotBlank
	@Column(length = 100, unique = true)
	private String email;

	@NotBlank
	@Column()
	private String password;
	
	@NotBlank
	@Column()
	private String nickName;

	@OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, fetch = FetchType.EAGER) 
	@Column()
	private List<ItemEntity> items;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

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

	public List<ItemEntity> getItems() {
		return items;
	}

	public void setItems(List<ItemEntity> items) {
		this.items = items;
	}
	
	public void addItem(ItemEntity item) {
		if(items == null) {
			items = new ArrayList<>();
		}
		items.add(item);
	}
	
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

}
