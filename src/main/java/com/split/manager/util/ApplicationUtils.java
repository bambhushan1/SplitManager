package com.split.manager.util;

import java.util.ArrayList;
import java.util.List;

import com.split.manager.entity.Bill;
import com.split.manager.entity.User;
import com.split.manager.vo.BillVO;
import com.split.manager.vo.UserVO;

public class ApplicationUtils {

	public static List<UserVO> getUserVO(List<User> users){
		List<UserVO> userVos =new ArrayList<>();
		if(users != null && users.size() > 0) {
			users.forEach(user ->{
				UserVO userVO = getUserVO(user);
				userVos.add(userVO);
			});
		}
		return userVos;
	}

	public static UserVO getUserVO(User user) {
		UserVO userVO = new UserVO();
		userVO.setFirstName(user.getFirstName());
		userVO.setLastName(user.getLastName());
		userVO.setEmail(user.getEmail());
		userVO.setId(user.getId());
		return userVO;
	}
	
	public static BillVO getBillVO(Bill bill) {
		BillVO billVO = new BillVO();
		billVO.setName(bill.getName());
		billVO.setDescription(bill.getDescription());
		billVO.setAmount(bill.getAmount());
		billVO.setId(bill.getId());
		return billVO;
	}
}
