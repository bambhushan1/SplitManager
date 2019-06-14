package com.split.manager.vo;

import java.math.BigDecimal;
import java.math.BigInteger;

public class BillDetailsVO {

	private Integer id;
	private String name;
	private String description;
	private Double amount;
	private Integer userCount;
	private Double sharedAmount;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Double getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount.doubleValue();
	}
	public Integer getUserCount() {
		return userCount;
	}
	public void setUserCount(BigInteger userCount) {
		this.userCount = userCount.intValue();
	}
	public Double getSharedAmount() {
		return sharedAmount;
	}
	public void setSharedAmount(BigDecimal sharedAmount) {
		this.sharedAmount = sharedAmount.doubleValue();
	}
	
	
}
