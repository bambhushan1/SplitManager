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
import com.split.manager.entity.User;
import com.split.manager.exception.ApplicationException;
import com.split.manager.util.ApplicationCode;

/**
 * This class is the DAO class used to handle Users of the application
 *  
 * @author Bhushan Mahajan
 *
 */
@Repository
public class UserDAO implements IUserDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public User getUserById(Integer userId) {
		Session session= null;
		User user= null;
		try {
			try {
				session = sessionFactory.openSession();
				Criteria criteria = session.createCriteria(User.class);
				criteria.add(Restrictions.isNull("deleted"));
				criteria.add(Restrictions.eq("id", userId));
				user = (User) criteria.uniqueResult();
			}finally {
				if(session != null) 
					session.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception Occurred In DB operation", ApplicationCode.BASE_DB_ERROR, e);
		}
		return user;	
	}
	
	@Override
	public User getUserByEmail(String email) {

		Session session= null;
		User user= null;
		try {
			try {
				session = sessionFactory.openSession();
				Criteria criteria = session.createCriteria(User.class);
				criteria.add(Restrictions.isNull("deleted"));
				criteria.add(Restrictions.eq("email", email));
				user = (User) criteria.uniqueResult();
			}finally {
				if(session != null) 
					session.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception Occurred In DB operation", ApplicationCode.BASE_DB_ERROR, e);
		}
		return user;	
	
	}

	@Override
	public List<User> getAllUsers() {
		Session session= null;
		List<User> users= null;
		try {
			try {
				session = sessionFactory.openSession();
				Criteria criteria = session.createCriteria(User.class);
				criteria.add(Restrictions.isNull("deleted"));
				criteria.addOrder(Order.asc("firstName"));
				criteria.addOrder(Order.asc("lastName"));
				criteria.addOrder(Order.asc("email"));
				users = criteria.list();
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
	public List<User> getUsersByIds(List<Integer> userIds) {
		Session session= null;
		List<User> users= null;
		try {
			try {
				session = sessionFactory.openSession();
				Criteria criteria = session.createCriteria(User.class);
				criteria.add(Restrictions.isNull("deleted"));
				criteria.add(Restrictions.in("id", userIds));
				criteria.addOrder(Order.asc("firstName"));
				criteria.addOrder(Order.asc("lastName"));
				criteria.addOrder(Order.asc("email"));
				users = criteria.list();
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
	public User createUser(User user) {
		Session session= null;
		try {
			session = sessionFactory.openSession();
			try {
				session.beginTransaction();
				session.save(user);
				session.getTransaction().commit();
			}finally {
				if(session != null) session.close();
		}
	} catch (Exception e) {
		e.printStackTrace();
		throw new ApplicationException("Exception Occurred In DB operation", ApplicationCode.BASE_DB_ERROR, e);
	}
		return user;
	}

	@Override
	public boolean deleteUser(User user) {
		Session session= null;
		try {
			try {
				session = sessionFactory.openSession();
				session.beginTransaction();
				user.setDeleted(new Date());
				session.update(user);
				deleteBillUserEnteries(session, user);
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
	public User updateUser(User user) {
		Session session= null;
		try {
			try {
				session = sessionFactory.openSession();
				session.beginTransaction();
				session.update(user);
				session.getTransaction().commit();
			}finally {
				if(session != null) session.close();
		}
	} catch (Exception e) {
		e.printStackTrace();
		throw new ApplicationException("Exception Occurred In DB operation", ApplicationCode.BASE_DB_ERROR, e);
	}
		return user;
	}
	
	private boolean deleteBillUserEnteries(Session session, User user) {
		SQLQuery sqlQuery = session.createSQLQuery("UPDATE bills_users SET deleted = now() WHERE user_id = :userId AND deleted IS NULL");
		sqlQuery.setParameter("userId", user.getId());
		sqlQuery.executeUpdate();
		return true;
	}
}
