package com.split.manager.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.split.manager.entity.Bill;
import com.split.manager.entity.BillUser;
import com.split.manager.entity.User;
import com.split.manager.exception.ApplicationException;
import com.split.manager.util.ApplicationCode;
import com.split.manager.util.ApplicationConstants;
import com.split.manager.vo.BillDetailsVO;
import com.split.manager.vo.BillVO;
import com.split.manager.vo.UserDetailsVO;
import com.split.manager.vo.UserVO;

/**
 * This class is the DAO class used to handle Bill and User mappings of the application
 *  
 * @author Bhushan Mahajan
 *
 */

@Repository
public class BillUserDAO implements IBillUserDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<UserDetailsVO> getBillUsers(Bill bill) {
		Session session= null;
		List<UserDetailsVO> users = null;
		try {
			try {
				session = sessionFactory.openSession();
				SQLQuery sqlQuery = session.createSQLQuery(ApplicationConstants.QUERY_GET_BILL_USERS);
				sqlQuery.setParameter("billId", bill.getId());
				sqlQuery.setResultTransformer(Transformers.aliasToBean(UserDetailsVO.class));
				users = sqlQuery.list();
			}finally {
				if(session != null) 
					session.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception Occurred In DB operation", ApplicationCode.BASE_DB_ERROR, e);
			
		}
		return users;
	}

	@Override
	public List<BillDetailsVO> getUserBills(User user) {
		Session session= null;
		List<BillDetailsVO> bills = null;
		try {
			try {
				session = sessionFactory.openSession();
				SQLQuery sqlQuery = session.createSQLQuery(ApplicationConstants.QUERY_GET_USER_BILLS);
				sqlQuery.setParameter("userId", user.getId());
				sqlQuery.setResultTransformer(Transformers.aliasToBean(BillDetailsVO.class));
				bills = sqlQuery.list();
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
	public BillUser createBillUser(BillUser billUser) {
		Session session= null;
		try {
			try {
				session = sessionFactory.openSession();
				session.beginTransaction();
				session.update(billUser);
				session.getTransaction().commit();
			}finally {
				if(session != null) session.close();
		}
	} catch (Exception e) {
		e.printStackTrace();
		throw new ApplicationException("Exception Occurred In DB operation", ApplicationCode.BASE_DB_ERROR, e);
	}
		return billUser;
	}

	@Override
	public boolean deleteBillUser(BillUser billUser) {
		Session session= null;
		try {
			try {
				session = sessionFactory.openSession();
				session.beginTransaction();
				billUser.setDeleted(new Date());
				session.update(billUser);
				session.getTransaction().commit();
				return true;
			}finally {
				if(session != null) session.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception Occurred In DB operation", ApplicationCode.BASE_DB_ERROR, e);
		}
	}

	@Override
	public boolean addBillWithUsers(Bill bill, List<User> users) {
		Session session= null;
		try {
			try {
				session = sessionFactory.openSession();
				session.beginTransaction();
				session.save(bill);
				if(users != null && users.size()> 0) {
					mapUsersToBill(session, users, bill);
				}
				session.getTransaction().commit();
				return true;
			}finally {
				if(session != null) session.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception Occurred In DB operation", ApplicationCode.BASE_DB_ERROR, e);
		}
	}
	
	@Override
	public boolean updateBillWithUsers(Bill bill, List<Integer> users) {
		Session session= null;
		try {
			try {
				session = sessionFactory.openSession();
				session.beginTransaction();
				session.update(bill);
				if(users != null && users.size()> 0) {
					modifiedBillUserList(session, bill, users);
				}
				session.getTransaction().commit();
				return true;
			}finally {
				if(session != null) session.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception Occurred In DB operation", ApplicationCode.BASE_DB_ERROR, e);
		}
	}
	
	private void mapUsersToBill(Session session, List<User> users, Bill bill) {
		Date date = new Date();
		users.forEach(user -> {
			BillUser billUser = getBillUserEntity(bill, user, date);
			session.save(billUser);
		});
	}

	
	
	private List<BillUser> modifiedBillUserList(Session session, Bill bill, List<Integer> users){
		List<BillUser> billUsers = new ArrayList<>();
		List<BillUser> allBillUsers = getAllBillUsers(session, bill);
		List<Integer> existingIds = new ArrayList<>();
		Date date = new Date();
		if(allBillUsers != null && allBillUsers.size() > 0) {
			allBillUsers.forEach(billUser ->{
				if(users.contains(billUser.getUser().getId())) {
					billUser.setDeleted(null);
					existingIds.add(billUser.getUser().getId());
				}else {
					billUser.setDeleted(date);
				}
				session.update(billUser);
			});
			
			users.removeAll(existingIds);
			if(users.size() > 0) {
				users.forEach(userId ->{
					User user = new User();
					user.setId(userId);
					session.save(getBillUserEntity(bill, user, date));
				});
			}
		}
		
		return billUsers;
	}
	
	private List<BillUser> getAllBillUsers(Session session, Bill bill){
		Criteria criteria = session.createCriteria(BillUser.class);
		criteria.add(Restrictions.eq("bill", bill));
		return criteria.list();
	}

	private BillUser getBillUserEntity(Bill bill, User user, Date date) {
		BillUser billUser= new BillUser();
		billUser.setBill(bill);
		billUser.setUser(user);
		billUser.setCreated(date);
		return billUser;
	}
	
}
