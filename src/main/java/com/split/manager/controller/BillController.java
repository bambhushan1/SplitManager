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
import com.split.manager.vo.BillUserRequestVO;
import com.split.manager.vo.RestResponse;

@Controller
@RequestMapping("/bill")
public class BillController {

	@Autowired
	private IApplicationServiceHandler applicationServiceHandler;
	
	@RequestMapping( method = { RequestMethod.POST })
	public ResponseEntity<RestResponse> postBill(@RequestBody BillUserRequestVO billUserRequestVO, HttpServletRequest httpRequest, HttpServletResponse httpResponse){
		return applicationServiceHandler.process(ApplicationRestAction.ADD_BILL, billUserRequestVO, null, httpRequest, httpResponse);
	}
	
	@RequestMapping( method =  RequestMethod.GET,  value = "/{"+ ApplicationConstants.PARAM_BILL_ID +"}" )
	public ResponseEntity<RestResponse> getBill(
			@PathVariable(ApplicationConstants.PARAM_BILL_ID) Integer billId,
			HttpServletRequest httpRequest, HttpServletResponse httpResponse){
		return applicationServiceHandler.process(ApplicationRestAction.GET_BILL, null, billId, httpRequest, httpResponse);
	}
	
	@RequestMapping( method = { RequestMethod.GET })
	public ResponseEntity<RestResponse> getBills( HttpServletRequest httpRequest, HttpServletResponse httpResponse){
		return applicationServiceHandler.process(ApplicationRestAction.GET_BILLS, null, null, httpRequest, httpResponse);
	}
	
	@RequestMapping( method =  RequestMethod.PUT,  value = "/{"+ ApplicationConstants.PARAM_BILL_ID +"}" )
	public ResponseEntity<RestResponse> updateBill(
			@PathVariable(ApplicationConstants.PARAM_BILL_ID) Integer billId,
			@RequestBody BillUserRequestVO billUserRequestVO,
			HttpServletRequest httpRequest, HttpServletResponse httpResponse){
		return applicationServiceHandler.process(ApplicationRestAction.UPDATE_BILL, billUserRequestVO, billId, httpRequest, httpResponse);
	}
	
	@RequestMapping( method =  RequestMethod.DELETE,  value = "/{"+ ApplicationConstants.PARAM_BILL_ID +"}" )
	public ResponseEntity<RestResponse> deleteBill(
			@PathVariable(ApplicationConstants.PARAM_BILL_ID) Integer billId,
			HttpServletRequest httpRequest, HttpServletResponse httpResponse){
		return applicationServiceHandler.process(ApplicationRestAction.DELETE_BILL, null, billId, httpRequest, httpResponse);
	}
}
