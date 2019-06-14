package com.split.manager.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.split.manager.config.ApplicationRestAction;
import com.split.manager.service.IBillService;
import com.split.manager.service.IUserService;
import com.split.manager.vo.BillUserRequestVO;
import com.split.manager.vo.RestResponse;
import com.split.manager.vo.UserVO;

@Component("applicationServiceHandler")
public class ApplicationServiceHandler implements IApplicationServiceHandler {

	private static Logger logger = LogManager.getLogger(ApplicationServiceHandler.class);
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IBillService billService;
	@Override
	public ResponseEntity<RestResponse> process(ApplicationRestAction action, Object param, Integer id,
			HttpServletRequest httpRequest, HttpServletResponse httpServletResponse) {
		logger.info("Resquested Action : " + action.toString());
		RestResponse response = null;
		Integer status = 200;
		switch (action) {
			case GET_USERS: 
				response = userService.getUsers();
				break;
			case ADD_USER:
				UserVO userVO = (UserVO) param;
				response = userService.addUser(userVO);
				break;
			case UPDATE_USER:
				UserVO userVO1 = (UserVO) param;
				response = userService.updateUser(id, userVO1);
				break;
			case GET_USER:
				response = userService.getUserWithBill(id);
				break;
			case DELETE_USER:
				response = userService.deleteUser(id);
				break;
			
			//Billing related requests
			case ADD_BILL:
				BillUserRequestVO billUserRequestVO = (BillUserRequestVO) param;
				response = billService.createBill(billUserRequestVO);
				break;
			case GET_BILL:
				response = billService.getBill(id);
				break;
			case GET_BILLS:
				response = billService.getBills();
				break;
			case UPDATE_BILL:
				BillUserRequestVO billUserRequestVO1 = (BillUserRequestVO) param;
				response = billService.updateBill(id, billUserRequestVO1);
				break;
			case DELETE_BILL:
				response = billService.deleteBill(id);
				break;
			default:
				response= new RestResponse(Response.Status.NOT_FOUND.getStatusCode(),
						"INVALID_ACTION",
						"Invalid Action");
		}
		return ResponseEntity.status(status).body(response);

	}

}
