package com.split.manager.vo;

import java.math.BigDecimal;

public class UserDetailsVO {

	private Integer id;
	private String first_name;
	private String last_name;
	private String email;
	private Double sharedAmount;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Double getSharedAmount() {
		return sharedAmount;
	}
	public void setSharedAmount(BigDecimal sharedAmount) {
		this.sharedAmount = sharedAmount.doubleValue();
	}
	
}
