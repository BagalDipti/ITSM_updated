package com.itsm.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.itsm.model.Algo;
import com.itsm.model.Mail;
import com.itsm.model.Request;
import com.itsm.model.User;

@Component
public class SchedulerService {
	@Autowired
	private SendMailService service;

	@Autowired
	private UserService userService;
	
	@Autowired
	private RequestService requestService;
	
	// after 5 min
	@Scheduled(initialDelay = 60000, fixedRate = 60000)
	public void runAlgorithm() {
		List<Request> requestAccepted = requestService.findByStatus("accepted");
		List<Integer> actionOwnerList = requestService.findActionOwner();
		if(requestAccepted.size()!=0 && actionOwnerList.size()!=0) {
			System.out.println("Algorithm executed at "+LocalDateTime.now());
			Algo algo = new Algo();
			algo.getRequestData(requestAccepted);
			HashMap<Integer, Integer> mapRequestActionowner = algo.priorityNonPreemptiveAlgorithm(actionOwnerList);
			requestService.updateAcceptedByCompleted("completed", "accepted");
			Iterator<Integer> requestIterator = mapRequestActionowner.keySet().iterator();
			Iterator<Integer> aoIterator = mapRequestActionowner.values().iterator();
			while (requestIterator.hasNext() && aoIterator.hasNext()) {
				int oneRequestId = (int) requestIterator.next();
				int oneAOId = (int) aoIterator.next();
				Request request = requestService.getSingle(oneRequestId);
				requestService.updateRequestId(oneRequestId,oneAOId);
				String status = requestService.findStatusById(request.getRequestId());
				User user = request.getUser();
				List<String> mailList = new ArrayList<>();
				User singleUser = this.userService.getUserById(user.getId());
				String managerMail = this.userService.getManagerEmailByUid(singleUser.getManagerId());
				mailList.add(singleUser.getUserEmail());
				mailList.add(managerMail);
				String subject = request.getSubject();
				String userName = user.getUserName();
				User manager = this.userService.getUserById(singleUser.getManagerId());
				String managerName = manager.getUserName();
				User actionOwner = this.userService.getUserById(oneAOId);
				String actionOwnerName = actionOwner.getUserName();
				String emailBody = "Hi,\n"
						+ "\n"
						+ "Request Id : "+oneRequestId+"\n"
						+ " \n"
						+ "Request Raised By : "+userName+"\n"
						+ "\n"
						+ "Approved By Manager : "+managerName+"\n"
						+ "\n"
						+ "Request Status : "+status+"\n"
						+ "\n"
						+ "Action Owner : Request completed by "+actionOwnerName+"\n"
						+ "\n"
						+ "Please check and confirm.\n"
						+ "\n"
						+ "Thanks & reguards,\n"
						+ "GSLab";
				service.sendMail(new Mail(mailList, request.getRequestId(), emailBody, subject));
//				service.sendMail(new Mail(mailList, request.getRequestId(), currentStatus, request.getSubject()));
			}
			System.out.println("Mail Send successfully.\nRequests are completed at "+LocalDateTime.now());
		}else {
			System.out.println("Requests are not available or Action Owner are not available");
		}
	}
	
	
	@Scheduled(cron = "10 * * * * *")
	public void scheduledMethod() {
//		System.out.println("Every 10th second-->"+LocalDateTime.now());
		/*
		List<String> mailList = this.userService.allUserEmail();
		System.out.println(mailList);
		String subject = "InfoSec & IT Compliance Assessment 2021 Course";
		String text = "Hi All,"
				+ "\n"
				+ "\n"
				+ "As you are aware every employee of GS Lab is required to go through the yearly compliance course and assessment to comply with the guidelines."
				+ "\n"
				+ "\n"
				+ "Request you to kindly dedicate some time for this and complete the pending activities at the earliest, please.";
		for(String singleMail:mailList) {
			List<String> oneMail = new ArrayList<>();
			oneMail.add(singleMail);
			service.sendMail(new Mail(oneMail, 1, text, subject));
		}*/
	}
}
