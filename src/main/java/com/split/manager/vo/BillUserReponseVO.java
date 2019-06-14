package com.split.manager.vo;

import java.util.List;

public class BillUserReponseVO {

	private BillVO bill;
	private List<UserVO> users;
	public BillVO getBill() {
		return bill;
	}
	public void setBill(BillVO bill) {
		this.bill = bill;
	}
	public List<UserVO> getUsers() {
		return users;
	}
	public void setUsers(List<UserVO> users) {
		this.users = users;
	}
}
