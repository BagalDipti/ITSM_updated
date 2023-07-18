package com.itsm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.itsm.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	@Query(value = "select user_email from user where id=:uId",nativeQuery = true)
	public String findManagerEmailByUid(@Param("uId") int id);
	
	@Query(value = "select * from user where id=:uId",nativeQuery = true)
	public User findUserById(@Param("uId") int id);

	public User findByUserName(String userName);

    
	
//	@Query(value = "SELECT user_email from user",nativeQuery = true)
//	public List<String> allUserEmail();


}
