package com.split.manager.dao;

import java.util.List;

import com.split.manager.entity.Bill;
import com.split.manager.entity.BillUser;
import com.split.manager.entity.User;
import com.split.manager.vo.BillDetailsVO;
import com.split.manager.vo.BillVO;
import com.split.manager.vo.UserDetailsVO;
import com.split.manager.vo.UserVO;

public interface IBillUserDAO {

	List<UserDetailsVO> getBillUsers(Bill bill);
	List<BillDetailsVO> getUserBills(User user);
	BillUser createBillUser(BillUser billUser);
	boolean deleteBillUser(BillUser billUser);
	boolean addBillWithUsers(Bill bill, List<User> users);
	boolean updateBillWithUsers(Bill bill, List<Integer> users);
}
