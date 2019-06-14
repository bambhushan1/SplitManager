package com.split.manager.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.split.manager.config.ApplicationRestAction;
import com.split.manager.handler.IApplicationServiceHandler;
import com.split.manager.util.ApplicationConstants;
import com.split.manager.vo.RestResponse;
import com.split.manager.vo.UserVO;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IApplicationServiceHandler applicationServiceHandler;
	
	@RequestMapping( method = { RequestMethod.POST })
	public ResponseEntity<RestResponse> postUser(@RequestBody UserVO user, HttpServletRequest httpRequest, HttpServletResponse httpResponse){
		return applicationServiceHandler.process(ApplicationRestAction.ADD_USER, user, null, httpRequest, httpResponse);
	}
	
	@RequestMapping(method =  RequestMethod.PUT, value = "/{"+ ApplicationConstants.PARAM_USER_ID +"}")
	public ResponseEntity<RestResponse> UpdateUser(
			@PathVariable(ApplicationConstants.PARAM_USER_ID) Integer userId,
			@RequestBody UserVO user, HttpServletRequest httpRequest, HttpServletResponse httpResponse){
		return applicationServiceHandler.process(ApplicationRestAction.UPDATE_USER, user, userId, httpRequest, httpResponse);
	}
	
	@RequestMapping( method = { RequestMethod.GET })
	public ResponseEntity<RestResponse> getUsers(HttpServletRequest httpRequest, HttpServletResponse httpResponse){
		return applicationServiceHandler.process(ApplicationRestAction.GET_USERS, null, null, httpRequest, httpResponse);
	}
	
	@RequestMapping( method =  RequestMethod.GET,  value = "/{"+ ApplicationConstants.PARAM_USER_ID +"}" )
	public ResponseEntity<RestResponse> getUser(
			@PathVariable(ApplicationConstants.PARAM_USER_ID) Integer userId,
			HttpServletRequest httpRequest, HttpServletResponse httpResponse){
		return applicationServiceHandler.process(ApplicationRestAction.GET_USER, null, userId, httpRequest, httpResponse);
	}
	
	@RequestMapping( method =  RequestMethod.DELETE,  value = "/{"+ ApplicationConstants.PARAM_USER_ID +"}" )
	public ResponseEntity<RestResponse> deleteUser(
			@PathVariable(ApplicationConstants.PARAM_USER_ID) Integer userId,
			HttpServletRequest httpRequest, HttpServletResponse httpResponse){
		return applicationServiceHandler.process(ApplicationRestAction.DELETE_USER, null, userId, httpRequest, httpResponse);
	}
}
