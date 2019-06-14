package com.split.manager.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

/**
 * This class represents the bill user entity for the Course Demo. 
 * @author Bhushan Mahajan
 *
 */
@Entity
@Table(name="bills_users")
public class BillUser extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	private Bill bill;
	private User user;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="bill_id", nullable=false)
	public Bill getBill() {
		return bill;
	}
	public void setBill(Bill bill) {
		this.bill = bill;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id", nullable=false)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
