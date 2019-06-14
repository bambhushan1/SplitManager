package com.split.manager.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.split.manager.entity.Bill;
import com.split.manager.exception.ApplicationException;
import com.split.manager.util.ApplicationCode;
@Repository
public class BillDAO implements IBillDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Bill> getAllBills() {
		Session session= null;
		List<Bill> bills = null;
		try {
			session = sessionFactory.openSession();
			try {
				session = sessionFactory.openSession();
				Criteria criteria = session.createCriteria(Bill.class);
				criteria.addOrder(Order.desc("id"));
				bills = criteria.list();
			}finally {
				if(session != null) 
					session.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception Occurred In DB operation", ApplicationCode.BASE_DB_ERROR, e);
		}
		return bills;
	}

	@Override
	public Bill getBillById(Integer billId) {
		Session session= null;
		Bill bill = null;
		try {
			session = sessionFactory.openSession();
			try {
				session = sessionFactory.openSession();
				Criteria criteria = session.createCriteria(Bill.class);
				criteria.add(Restrictions.eq("id", billId));
				 bill = (Bill) criteria.uniqueResult();
				
			}finally {
				if(session != null) 
					session.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception Occurred In DB operation", ApplicationCode.BASE_DB_ERROR, e);
		}
		return bill;
	}

	@Override
	public boolean deleteBill(Bill bill) {
		Session session= null;
		try {
			session = sessionFactory.openSession();
			try {
				session.beginTransaction();
				bill.setDeleted(new Date());
				session.update(bill);
				deleteBillUserEnteries(session, bill);
				session.getTransaction().commit();
			}finally {
				if(session != null) 
					session.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception Occurred In DB operation", ApplicationCode.BASE_DB_ERROR, e);
		}
		return false;
	}

	@Override
	public Bill addBill(Bill bill) {
		Session session= null;
		try {
			try {
				session = sessionFactory.openSession();
				session.beginTransaction();
				session.save(bill);
				session.getTransaction().commit();
			}finally {
				if(session != null) 
					session.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception Occurred In DB operation", ApplicationCode.BASE_DB_ERROR, e);
		}
		return bill;
	}
	
	private boolean deleteBillUserEnteries(Session session, Bill bill) {
		SQLQuery sqlQuery = session.createSQLQuery("UPDATE bills_users SET deleted = now() WHERE bill_id = :billId AND deleted IS NULL");
		sqlQuery.setParameter("billId", bill.getId());
		sqlQuery.executeUpdate();
		return true;
	}

}
