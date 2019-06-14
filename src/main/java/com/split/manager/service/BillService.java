package com.split.manager.service;

import java.util.ArrayList;
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
import com.split.manager.vo.BillUserReponseVO;
import com.split.manager.vo.BillUserRequestVO;
import com.split.manager.vo.BillUsersReponseVO;
import com.split.manager.vo.BillVO;
import com.split.manager.vo.RestResponse;
import com.split.manager.vo.UserDetailsVO;
import com.split.manager.vo.UserVO;

@Service("billService")
public class BillService implements IBillService {

	@Autowired
	private IUserDAO userDAO;
	
	@Autowired
	private IBillDAO billDAO;
	
	@Autowired
	private IBillUserDAO billUserDAO;
	
	@Override
	public RestResponse createBill(BillUserRequestVO billUserRequestVO) {
		RestResponse response = new RestResponse(Response.Status.OK.getStatusCode(),
				"OK",
				"Bill Created");
		
		if(billUserRequestVO == null || billUserRequestVO.getName() == null || billUserRequestVO.getAmount() == null || billUserRequestVO.getUsers() == null || billUserRequestVO.getUsers().size() <=0) {
			return new RestResponse(Response.Status.BAD_REQUEST.getStatusCode(), ApplicationCode.INVALID_INPUT.getCodeId(), ApplicationCode.INVALID_INPUT.toString());
		}
		
		List<User> users = userDAO.getUsersByIds(billUserRequestVO.getUsers());
		if(users == null && users.size() != billUserRequestVO.getUsers().size()) {
			return new RestResponse(Response.Status.BAD_REQUEST.getStatusCode(), ApplicationCode.INVALID_INPUT.getCodeId(), ApplicationCode.INVALID_INPUT.toString());

		}
		Bill bill = new Bill();
		bill = getBillEntity(bill, billUserRequestVO);
		billUserDAO.addBillWithUsers(bill, users);
		BillUserReponseVO responseVO = populateResponseVO(bill, users);
		response.setData(responseVO);
		return response;
	}

	@Override
	public RestResponse getBill(Integer billId) {
		RestResponse response = new RestResponse(Response.Status.OK.getStatusCode(),
				"OK",
				"Bill Details");
		Bill bill = billDAO.getBillById(billId);
		if(bill == null) {
			return new RestResponse(Response.Status.BAD_REQUEST.getStatusCode(), ApplicationCode.BILL_NOT_FOUND.getCodeId(), ApplicationCode.BILL_NOT_FOUND.toString());
		}else {
			List<UserDetailsVO> billUsers = billUserDAO.getBillUsers(bill);
			BillUsersReponseVO billUserReponseVO = new BillUsersReponseVO();
			billUserReponseVO.setBill(ApplicationUtils.getBillVO(bill));
			billUserReponseVO.setUsers(billUsers);
			response.setData(billUserReponseVO);
		}
		return response;
	}
	
	@Override
	public RestResponse updateBill(Integer billId, BillUserRequestVO billUserRequestVO) {
		RestResponse response = new RestResponse(Response.Status.OK.getStatusCode(),
				"OK",
				"Bill Updated");
		Bill bill = billDAO.getBillById(billId);
		if(bill == null) {
			return new RestResponse(Response.Status.BAD_REQUEST.getStatusCode(), ApplicationCode.BILL_NOT_FOUND.getCodeId(), ApplicationCode.BILL_NOT_FOUND.toString());
		}else {
			bill = getBillEntity(bill, billUserRequestVO);
			List<Integer> billUsers = billUserRequestVO.getUsers();
			if(billUsers != null && billUsers.size() > 0) {
				List<User> users = userDAO.getUsersByIds(billUsers);
				if(users == null && users.size() != billUserRequestVO.getUsers().size()) {
					return new RestResponse(Response.Status.BAD_REQUEST.getStatusCode(), ApplicationCode.INVALID_INPUT.getCodeId(), ApplicationCode.INVALID_INPUT.toString());
				}
			}
			billUserDAO.updateBillWithUsers(bill, billUsers);
		}
		return response;
	}
	
	@Override
	public RestResponse deleteBill(Integer billId) {
		RestResponse response = new RestResponse(Response.Status.OK.getStatusCode(),
				"OK",
				"Bill Deleted");
		Bill bill = billDAO.getBillById(billId);
		if(bill == null) {
			return new RestResponse(Response.Status.BAD_REQUEST.getStatusCode(), ApplicationCode.BILL_NOT_FOUND.getCodeId(), ApplicationCode.BILL_NOT_FOUND.toString());
		}else {
			billDAO.deleteBill(bill);
		}
		return response;
	}
	
	@Override
	public RestResponse getBills() {
		List<Bill> allBills = billDAO.getAllBills();
		final List<BillVO> bills = new ArrayList<>();
		if(allBills != null && allBills.size() > 0) {
			allBills.forEach(bill -> {
				bills.add(ApplicationUtils.getBillVO(bill));
			});
		}
		RestResponse response = new RestResponse(Response.Status.OK.getStatusCode(),
				"OK",
				"Bill Details");
		response.setData(bills);
		return response;
	}

	private Bill getBillEntity(Bill bill, BillUserRequestVO billUserRequestVO) {
		bill.setName(billUserRequestVO.getName());
		bill.setDescription(billUserRequestVO.getDescription());
		bill.setAmount(billUserRequestVO.getAmount());
		if(bill.getId() == null)
		bill.setCreated(new Date());
		return bill;
	}
	
	private BillUserReponseVO populateResponseVO(Bill bill, List<User> users) {
		BillUserReponseVO billUserReponseVO = new BillUserReponseVO();
		billUserReponseVO.setBill(ApplicationUtils.getBillVO(bill));
		billUserReponseVO.setUsers(ApplicationUtils.getUserVO(users));
		return billUserReponseVO;
	}
}
