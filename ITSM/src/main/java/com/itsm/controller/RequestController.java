package com.itsm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;

import com.itsm.config.AppUser;
import com.itsm.config.LoggedInUser;
import com.itsm.model.Algo;
import com.itsm.model.Mail;
import com.itsm.service.SendMailService;
import com.itsm.service.UserService;
import com.itsm.model.Request;
import com.itsm.model.User;
import com.itsm.service.RequestService;

@RestController
@CrossOrigin("*")
public class RequestController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SendMailService service;

	@Autowired
	private RequestService requestService;

	@Autowired
	private UserService userService;

	// To create request

	@PostMapping("/createRequest/{userId}")
	public Object createRequest(@RequestBody Request request, @PathVariable int userId, @LoggedInUser AppUser appUser) {
		
		
		if(appUser.getUser().getId() == userId)
		{

			
			 Request request1 = this.requestService.createRequest(request);
			 User user = request.getUser();

			 
			 List<String> ls = new ArrayList<>();

			 User user2 = this.userService.getUserById(user.getId());
			 String managerMail =
			 this.userService.getManagerEmailByUid(user2.getManagerId());

			 ls.add(user2.getUserEmail());
			 System.out.println("----------------------" +user2.getUserEmail());

			 ls.add(managerMail);

			 System.out.println("------------------------" +user2.getManagerId());
			 String subject = request1.getSubject();
			 String userName = user2.getUserName();
			 String emailBody = 
					 "\nHi "+userName+
					 ",\n\nYour request for subject : "+subject.toUpperCase()+
					 " has been RAISED.\n\nPlease check and confirm.\n"
					 + "\n\n"
			 + "Thanks & Regards\n"
			 + "GSLab";
			 service.sendMail(new Mail(ls, request1.getRequestId(), emailBody, subject));

			 return "Email Sent successfully";
		}
		else
			return "Wrong Credential";

	}
	

	// To get all request that Created by user

	@GetMapping("/showRequestOfUser/{userId}")
	public ResponseEntity<?> getAllReqByUser(@PathVariable Integer userId,@LoggedInUser AppUser appUser) {
		logger.info("Getting details of user Id : " + userId);
		
		
		if(appUser.getUser().getId() == userId)
		{
			return ResponseEntity.ok(this.requestService.getAllRequestCreatedByUser(userId));
		}
		else
		{
			User user = this.userService.getUserById(userId);
			return ResponseEntity.badRequest().body("Wrong Credential for user "+user.getUserName());
		}
			
	}

	// To check request for manager approval

	@GetMapping("/ReqForManagerApproval/{managerId}")
	public ResponseEntity<?> getAllReqForManager(@PathVariable Integer managerId,@LoggedInUser AppUser appUser) {
		logger.info("Request for manager approval: " + managerId);
		
		if(appUser.getUser().getId() == managerId)
		{
			return ResponseEntity.ok(this.requestService.getAllRequestForManager(managerId));
		}
		else
		{
			User user = this.userService.getUserById(managerId);
			return ResponseEntity.badRequest().body("This Credential does not belong to "+user.getUserName()+" with id : "+managerId);
		}
			
	}

	// To approve or Disapprove the request

	@PutMapping("/ManagerActionOnRequest/{requestId}")
	public ResponseEntity<?> approveRequest(@RequestBody Request request, @PathVariable Integer requestId,@LoggedInUser AppUser appUser) {

		logger.info("Manager Action on request: " + requestId);
		
		int userId = this.requestService.getUserIdByRequestId(requestId);
		
		
		//User user = this.requestService.getUserByRequestId(requestId);
		
		//int managerId = this.userService.getManagerIdByUserId(user.getId());
		
		if(appUser.getUser().getId() == userId)
		{
			Request request2 = this.requestService.updateRequest(request, requestId);

			String userMail = this.userService.getManagerEmailByUid(request2.getUser().getId());
			User user2 = this.userService.getUserById(request2.getUser().getId());

			List<String> ls = new ArrayList<>();

			ls.add(userMail);
			String subject = request2.getSubject();
			String userName = user2.getUserName();
			String emailBody = "\nHi " + userName + ",\n\nYour request for subject : ' " + subject.toUpperCase()
					+ " ' has been " + request.getStatus().toUpperCase() + ".\n\nPlease check and confirm.\n" + "\n\n"
					+ "Thanks & Regards\n" + "GSLab";
			service.sendMail(new Mail(ls, request2.getRequestId(), emailBody, subject));

//			service.sendMail(new Mail(ls, request2.getRequestId(), request2.getStatus(), request2.getSubject()));

			return ResponseEntity.ok("\nEmail Sent successfully");
		}
		else
		{
			User user = this.userService.getUserById(userId);
			return new ResponseEntity<String>("This Credential does not belong to "+user.getUserName()+" with id : "+userId, HttpStatus.UNAUTHORIZED);
		}
		
		
		

	}

	/*
	 * // Algoritham
	 * 
	 * @RequestMapping("/algorithm") public String runAlgo() {
	 * logger.info("Priority Algorithm running"); List<Request> requestAccepted =
	 * requestService.findByStatus("accepted"); Algo algo = new Algo();
	 * algo.getRequestData(requestAccepted); List<Integer> actionOwnerList =
	 * requestService.findActionOwner(); HashMap<Integer, Integer>
	 * mapRequestActionowner = algo.priorityNonPreemptiveAlgorithm(actionOwnerList);
	 * requestService.updateAcceptedByCompleted("completed", "accepted");
	 * Iterator<Integer> it = mapRequestActionowner.keySet().iterator();
	 * Iterator<Integer> it2 = mapRequestActionowner.values().iterator(); while
	 * (it.hasNext() && it2.hasNext()) { int key = (int) it.next(); int val = (int)
	 * it2.next(); Request request = requestService.getSingle(key);
	 * requestService.updateRequestId(key,val); String sts =
	 * requestService.findStatusById(request.getRequestId()); User user =
	 * request.getUser(); List<String> ls = new ArrayList<>(); User user2 =
	 * this.userService.getUserById(user.getId()); String managerMail =
	 * this.userService.getManagerEmailByUid(user2.getManagerId());
	 * ls.add(user2.getUserEmail()); ls.add(managerMail); service.sendMail(new
	 * Mail(ls, request.getRequestId(), sts, request.getSubject())); } return
	 * mapRequestActionowner.toString() + "\nEmail Send successfully"; }
	 */
}
