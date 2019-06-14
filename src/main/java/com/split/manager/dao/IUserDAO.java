package com.split.manager.dao;

import java.util.List;

import com.split.manager.entity.User;

public interface IUserDAO {

	List<User> getAllUsers();
	List<User> getUsersByIds(List<Integer> userIds);
	User createUser(User user);
	boolean deleteUser(User user);
	User updateUser(User user);
	User getUserById(Integer userId);
	User getUserByEmail(String email);
}
