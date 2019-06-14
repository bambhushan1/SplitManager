package com.split.manager.vo;

import java.util.List;

public class BillUsersReponseVO {

	private BillVO bill;
	private List<UserDetailsVO> users;
	
	public BillVO getBill() {
		return bill;
	}
	public void setBill(BillVO bill) {
		this.bill = bill;
	}
	public List<UserDetailsVO> getUsers() {
		return users;
	}
	public void setUsers(List<UserDetailsVO> users) {
		this.users = users;
	}
}
