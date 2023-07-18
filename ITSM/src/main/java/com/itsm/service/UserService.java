package com.itsm.service;

import java.util.List;
import java.util.Set;

import com.itsm.model.User;

import com.itsm.model.UserDto;


public interface UserService {

	public User addRole(User user);

	public User setManagerID(User user, Integer userId);

	public Set<User> getAll();

	public User setIdAndPassword(User user, Integer userId);

	public User getUserById(int id);

	public String getManagerEmailByUid(int managerId);

	User save(UserDto user);
    List<User> findAll();
    User findOne(String userName);

//	public List<String> allUserEmail();

}
