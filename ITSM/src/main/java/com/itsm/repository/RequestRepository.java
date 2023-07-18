package com.itsm.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.type.TrueFalseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.itsm.model.Request;
import com.itsm.model.User;


public interface RequestRepository extends JpaRepository<Request,Integer>
{
	
	public List<Request> findByStatus(String status);

	@Query(value = "select * from request where user_id=:uId",nativeQuery = true)
	public List<Request> getAllRequestCreatedByUser(@Param("uId") Integer userId);
    
	
	@Query(value = "select request_id from request inner join user on request.user_id=user.id where manager_id=:mId", nativeQuery = true)
	public List<Integer> getAllRequestForManager(@Param("mId") Integer managerId);


	@Query(value = "SELECT user_id from request WHERE request_id=:uId",nativeQuery = true)
	public int getUserIdByRequestId(@Param("uId") Integer requestId);

	
	@Query(value = "SELECT id from user WHERE role='Action Owner'",nativeQuery = true)
	public List<Integer> findActionOwner();

	
	@Transactional
	@Modifying
	@Query("UPDATE Request SET status = ?1 WHERE status = ?2")
	void updateAcceptedByCompleted(String status1, String status2);

	@Transactional
	@Modifying
	@Query("UPDATE Request SET action_owner_id = ?2 WHERE request_id = ?1")
	public void updateRequestId(int key, int val);

	
	@Query("SELECT r.status FROM Request r WHERE requestId=:r1 ")
	public String findStatusById(@Param("r1") Integer requestId);
	


}
