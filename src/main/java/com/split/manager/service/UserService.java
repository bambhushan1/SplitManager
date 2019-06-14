package com.split.manager.service;

import java.util.Date;
import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.split.manager.dao.IBillDAO;
import com.split.manager.dao.IBillUserDAO;
import com.split.manager.dao.IUserDAO;
import com.split.manager.entity.Bill;
import com.split.manager.entity.User;
import com.split.manager.util.ApplicationCode;
import com.split.manager.util.ApplicationUtils;
import com.split.manager.vo.BillDetailsVO;
import com.split.manager.vo.BillUserReponseVO;
import com.split.manager.vo.BillVO;
import com.split.manager.vo.RestResponse;
import com.split.manager.vo.UserBillsResponseVO;
import com.split.manager.vo.UserVO;

@Service("userService")
public class UserService implements IUserService {

	@Autowired
	private IUserDAO userDAO;
	
	@Autowired
	private IBillDAO billDAO;
	
	@Autowired
	private IBillUserDAO billUserDAO;
	
	@Override
	public RestResponse getUsers() {
		RestResponse response = new RestResponse(Response.Status.OK.getStatusCode(),
				"OK",
				"User list");
		List<User> allUsers = userDAO.getAllUsers();
		if(allUsers != null && allUsers.size() > 0)
			response.setData(allUsers);
		return response;
	}

	@Override
	public RestResponse addUser(UserVO userVO) {
		if(userVO == null || userVO.getEmail() == null || userVO.getEmail().trim().equals("")) {
			return new RestResponse(Response.Status.BAD_REQUEST.getStatusCode(),
					"INVALID_USER_INPUT",
					"Invalid User Details.");
		}
		if(userDAO.getUserByEmail(userVO.getEmail()) != null)
			return new RestResponse(Response.Status.BAD_REQUEST.getStatusCode(),
					"INVALID_USER_INPUT",
					"User Already Exists.");
		
		RestResponse response = new RestResponse(Response.Status.CREATED.getStatusCode(),
				"USER_CREATED",
				"User Created");
		
		User user = new User();
		user.setFirstName(userVO.getFirstName());
		user.setLastName(userVO.getLastName());
		user.setEmail(userVO.getEmail());
		user.setCreated(new Date());
		user = userDAO.createUser(user);
		if(user!= null) {
			response.setData(user);
		}
		return response;
	}

	@Override
	public RestResponse getUserWithBill(Integer userId) {
		RestResponse response = new RestResponse(Response.Status.OK.getStatusCode(),
				"OK",
				"User Details");
		User user = userDAO.getUserById(userId);
		if(user == null) {
			return new RestResponse(Response.Status.BAD_REQUEST.getStatusCode(), ApplicationCode.USER_NOT_FOUND.getCodeId(), ApplicationCode.USER_NOT_FOUND.toString());
		}else {
			List<BillDetailsVO> userBills = billUserDAO.getUserBills(user);
			UserBillsResponseVO userBillsResponseVO= new UserBillsResponseVO();
			userBillsResponseVO.setUser(ApplicationUtils.getUserVO(user));
			userBillsResponseVO.setBills(userBills);
			response.setData(userBillsResponseVO);
		}
		return response;
	}

	@Override
	public RestResponse updateUser(Integer userId, UserVO userVO) {
		RestResponse response = new RestResponse(Response.Status.OK.getStatusCode(),
				"OK",
				"User Updated");
		User user = userDAO.getUserById(userId);
		if(user == null) {
			return new RestResponse(Response.Status.BAD_REQUEST.getStatusCode(), ApplicationCode.USER_NOT_FOUND.getCodeId(), ApplicationCode.USER_NOT_FOUND.toString());
		}else {
			user.setFirstName(userVO.getFirstName());
			user.setLastName(userVO.getLastName());
			user.setEmail(userVO.getEmail());
			user = userDAO.updateUser(user);
			if(user!= null) {
				response.setData(user);
			}
		}
		return response;
	}

	@Override
	public RestResponse deleteUser(Integer userId) {
		RestResponse response = new RestResponse(Response.Status.OK.getStatusCode(),
				"OK",
				"User Deleted");
		User user = userDAO.getUserById(userId);
		if(user == null) {
			return new RestResponse(Response.Status.BAD_REQUEST.getStatusCode(), ApplicationCode.USER_NOT_FOUND.getCodeId(), ApplicationCode.USER_NOT_FOUND.toString());
		}else {
			userDAO.deleteUser(user);
		}
		return response;
	}
	
	
	

}
