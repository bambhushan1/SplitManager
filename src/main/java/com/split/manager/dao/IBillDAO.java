package com.split.manager.dao;

import java.util.List;

import com.split.manager.entity.Bill;

public interface IBillDAO {

	List<Bill> getAllBills();
	Bill getBillById(Integer billId);
	Bill addBill(Bill bill);
	boolean deleteBill(Bill bill);
}
