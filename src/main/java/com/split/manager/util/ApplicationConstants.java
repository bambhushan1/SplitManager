package com.split.manager.util;

public class ApplicationConstants {

	public static final String PARAM_BILL_ID = "billId";
	public static final String PARAM_USER_ID = "userId";
	public static final String QUERY_GET_USER_BILLS = "SELECT bills.id, bills.name, bills.description, bills.amount, userCount, sharedAmount\n" + 
			"FROM bills_users INNER JOIN bills ON bills.id = bills_users.bill_id AND bills.deleted IS NULL\n" + 
			"LEFT JOIN (Select bills.id,  bills.amount/count(user_id) As sharedAmount, count(user_id) as userCount from bills\n" + 
			"LEFT JOIN bills_users ON bills_users.bill_id = bills.id AND bills_users.deleted IS NULL\n" + 
			"WHERE bills.deleted IS NULL\n" + 
			"GROUP BY bills.id) tmp ON tmp.id = bills_users.bill_id\n" + 
			"WHERE bills_users.user_id = :userId\n" + 
			"ORDER BY bills.id DESC";
	
	public static final String QUERY_GET_BILL_USERS = "SELECT users.id, users.first_name, users.last_name, users.email, sharedAmount\n" + 
			"FROM bills_users INNER JOIN users ON users.id = bills_users.user_id AND users.deleted IS NULL\n" + 
			"LEFT JOIN (Select bills.id,  bills.amount/count(user_id) As sharedAmount, count(user_id) as userCount from bills\n" + 
			"LEFT JOIN bills_users ON bills_users.bill_id = bills.id AND bills_users.deleted IS NULL\n" + 
			"WHERE bills.deleted IS NULL\n" + 
			"GROUP BY bills.id) tmp ON tmp.id = bills_users.bill_id\n" + 
			"WHERE bills_users.bill_id = :billId\n" + 
			"ORDER BY users.first_name ASC, users.last_name ASC";
	
}
