package com.split.manager.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * This class represents the user entity for the Split Manager Demo. 
 * @author Bhushan Mahajan
 *
 */
@Entity
@Table(name="users")
public class User extends BaseEntity {

	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	private String email;
	
	@Column(name="first_name",length=45)
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	@Column(name="last_name",length=45)
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Column(name="email",length=256)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
