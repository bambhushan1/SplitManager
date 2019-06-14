package com.split.manager.service;

import com.split.manager.vo.BillUserRequestVO;
import com.split.manager.vo.RestResponse;

public interface IBillService {

	RestResponse createBill(BillUserRequestVO billUserRequestVO);
	RestResponse getBill(Integer billId);
	RestResponse getBills();
	RestResponse updateBill(Integer billId, BillUserRequestVO billUserRequestVO);
	RestResponse deleteBill(Integer billId);
}
