package com.split.manager.vo;

import java.util.List;

public class UserBillsResponseVO {

	private UserVO user;
	private List<BillDetailsVO> bills;
	
	public UserVO getUser() {
		return user;
	}

	public void setUser(UserVO user) {
		this.user = user;
	}

	public List<BillDetailsVO> getBills() {
		return bills;
	}

	public void setBills(List<BillDetailsVO> bills) {
		this.bills = bills;
	}

	
}
