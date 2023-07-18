package com.itsm.service;

import java.util.List;
import java.util.Set;



import com.itsm.model.Request;
import com.itsm.model.User;

public interface RequestService {

	public Request createRequest(Request request);

	public Set<Request> getAllRequestCreatedByUser(Integer userId);

	public Set<Integer> getAllRequestForManager(Integer managerId);

	public Request approveRequest(Request request, Integer requestId);

	public int getUserIdByRequestId(Integer requestId);

	public Request updateRequest(Request request, Integer requestId);

	public List<Request> findByStatus(String string);
//
	public List<Integer> findActionOwner();
//
	public void updateAcceptedByCompleted(String string, String string2);
//
	public Request getSingle(int key);
//
	public void updateRequestId(int key, int val);
//
	public String findStatusById(int requestId);

	public User getManagerIdByUserId(int userId);
	
	//public List<Request> findByStatus(String status);


	/*
	 * public Request add(Request Request);
	 * 
	 * public Request update(Request request,Integer requestId);
	 * 
	 * public Set<Request> getAll();
	 * 
	 * public Request getSingle(Integer id);
	 * 
	 * public void deleteById(Integer id);
	 * 
	 * public List<Request> findByStatus(String status);
	 * 
	 * public void updateAcceptedByCompleted(String status1, String status2);
	 * 
	 * public String findStatusById(Integer requestId);
	 * 
	 * public String getEmail(Integer requestId);
	 * 
	 * public Set<Request> getAllRequestBymanagerId(Integer managerId);
	 * 
	 * public Set<Request> getAllRequestByUserId(Integer userId);
	 * 
	 * public Set<Request> getAllRequestByActionOwnerIdId(Integer actionOwnerId);
	 * 
	 * public Set<Request> findAllRequestOfmanager(Integer userId);
	 * 
	 * public String getEmailOfManager(Integer requestId);
	 * 
	 * public String getActionOwnerEmailById(Integer requestId);
	 * 
	 * public void updateRequestId(int key,int val);
	 */
	

	
}
