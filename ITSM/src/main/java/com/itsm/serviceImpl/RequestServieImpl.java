package com.itsm.serviceImpl;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itsm.model.Request;
import com.itsm.model.User;
import com.itsm.repository.RequestRepository;

import com.itsm.service.RequestService;

@Service
public class RequestServieImpl implements RequestService {

	@Autowired
	private RequestRepository requestRepository;

	@Override
	public Request createRequest(Request request) {
		return this.requestRepository.save(request);
	}

	@Override
	public Set<Request> getAllRequestCreatedByUser(Integer userId) {
		return new LinkedHashSet<>(this.requestRepository.getAllRequestCreatedByUser(userId));
	}

	@Override
	public Set<Integer> getAllRequestForManager(Integer managerId) {

		return new LinkedHashSet<>(this.requestRepository.getAllRequestForManager(managerId));
	}

	@Transactional
	@Override
	public Request approveRequest(Request request, Integer requestId) {

		Request request1 = this.requestRepository.getOne(requestId);
		request1.setStatus(request.getStatus());
		request1.setBurstTime(request.getBurstTime());

		return this.requestRepository.save(request1);

	}

	@Override
	public int getUserIdByRequestId(Integer requestId) {
		return this.requestRepository.getUserIdByRequestId(requestId);
	}

	@Override
	public Request updateRequest(Request request, Integer requestId) {

		Request request1 = this.requestRepository.getOne(requestId);

		request1.setStatus(request.getStatus().toLowerCase());

		request1.setBurstTime(request.getBurstTime());

		return this.requestRepository.save(request1);
	}

	@Override
	public List<Request> findByStatus(String status) {
		return this.requestRepository.findByStatus(status);
	}

	@Override
	public List<Integer> findActionOwner() {
		return this.requestRepository.findActionOwner();
	}

	@Override
	public void updateAcceptedByCompleted(String status1, String status2) {
		this.requestRepository.updateAcceptedByCompleted(status1, status2);

	}

//
//
	@Override
	public Request getSingle(int key) {

		return this.requestRepository.findById(key).get();
	}

//
//	
//
	@Override
	public void updateRequestId(int key, int val) {

		this.requestRepository.updateRequestId(key, val);
	}

	@Override
	public String findStatusById(int requestId) {
		// TODO Auto-generated method stub
		return this.requestRepository.findStatusById(requestId);
	}

	@Override
	public User getManagerIdByUserId(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
