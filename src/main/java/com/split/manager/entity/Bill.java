package com.split.manager.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

/**
 * This class represents the bill entity for the Split Manager Demo. 
 * @author Bhushan Mahajan
 *
 */
@Entity
@Table(name="bills")
@Where(clause = "deleted IS NULL")
public class Bill extends BaseEntity {

	private static final long serialVersionUID = 1L;
	private String name;
	private String description;
	private Double amount;
	
	@Column(name="name",length=45)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="description",length=256)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name="amount")
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
}
