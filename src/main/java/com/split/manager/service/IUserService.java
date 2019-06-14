package com.split.manager.service;

import com.split.manager.vo.RestResponse;
import com.split.manager.vo.UserVO;

public interface IUserService {

	RestResponse getUsers();
	RestResponse addUser(UserVO userVO);
	RestResponse updateUser(Integer userId, UserVO userVO);
	RestResponse getUserWithBill(Integer userId);
	RestResponse deleteUser(Integer userId);
}
