package com.itsm.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.itsm.model.Request;

public class Algo {
	int burstTime[];
	int priority[];
	int arrivalTime[];
	int[] requestId;
	int completionTime[];
	int numberOfRequest;
	Queue<Integer> executionQueue;

	public void getRequestData(List<Request> requestAccepted) {
		int inputNumberOfRequest = requestAccepted.size();
		numberOfRequest = inputNumberOfRequest;
		burstTime = new int[numberOfRequest];
		priority = new int[numberOfRequest];
		arrivalTime = new int[numberOfRequest];
		requestId = new int[numberOfRequest];
        LocalDateTime startDateTime = LocalDateTime.parse(LocalDateTime.now().toString());
        HashMap<String, Integer> priorityLevel = new HashMap<String, Integer>();
        priorityLevel.put("high", 1);
        priorityLevel.put("medium", 2);
        priorityLevel.put("low", 3);
		for (int i = 0; i < numberOfRequest; i++) {
			Request singleRequest = requestAccepted.get(i);
            requestId[i] = singleRequest.getRequestId(); 
            burstTime[i] = singleRequest.getBurstTime(); 
            LocalDateTime curreDateTime = singleRequest.getArrivalTime();
            long dateTimeDifference = ChronoUnit.SECONDS.between(curreDateTime, startDateTime);
            int dateTimeDiff = (int) Math.abs(dateTimeDifference);
            arrivalTime[i] = dateTimeDiff;
            priority[i] = priorityLevel.get(singleRequest.getPriority());
		}
	}

	public void sortAccordingArrivalTimeAndPriority(int[] arrivalTime, int[] burstTime, int[] priority, int[] requestId) {
		int temp;
		int tempRequestId;
		for (int i = 0; i < numberOfRequest; i++) {
			for (int j = 0; j < numberOfRequest - i - 1; j++) {
				if (arrivalTime[j] > arrivalTime[j + 1]) {
					temp = arrivalTime[j];
					arrivalTime[j] = arrivalTime[j + 1];
					arrivalTime[j + 1] = temp;
					temp = burstTime[j];
					burstTime[j] = burstTime[j + 1];
					burstTime[j + 1] = temp;
					temp = priority[j];
					priority[j] = priority[j + 1];
					priority[j + 1] = temp;
					tempRequestId = requestId[j];
					requestId[j] = requestId[j + 1];
					requestId[j + 1] = tempRequestId;
				}
				if (arrivalTime[j] == arrivalTime[j + 1]) {
					if (priority[j] > priority[j + 1]) {
						temp = arrivalTime[j];
						arrivalTime[j] = arrivalTime[j + 1];
						arrivalTime[j + 1] = temp;
						temp = burstTime[j];
						burstTime[j] = burstTime[j + 1];
						burstTime[j + 1] = temp;
						temp = priority[j];
						priority[j] = priority[j + 1];
						priority[j + 1] = temp;
						tempRequestId = requestId[j];
						requestId[j] = requestId[j + 1];
						requestId[j + 1] = tempRequestId;
					}
				}
			}
		}
	}

	public HashMap<Integer, Integer> priorityNonPreemptiveAlgorithm(List<Integer> actionOwnerList) {
		completionTime = new int[numberOfRequest];
		executionQueue = new LinkedList<>();
		int bt[] = burstTime.clone();
		int at[] = arrivalTime.clone();
		int prt[] = priority.clone();
		int rid[] = requestId.clone();
		sortAccordingArrivalTimeAndPriority(at, bt, prt, rid);
		completionTime[0] = at[0] + bt[0];
		for (int i = 1; i < numberOfRequest; i++) {
			completionTime[i] = bt[i] + completionTime[i - 1];
		}
		for (int i = 0; i < numberOfRequest; i++) {
			executionQueue.add(rid[i]);
		}
		int noOfActionOwner = actionOwnerList.size();
		int[] actionOwnerId = new int[noOfActionOwner];
		for (int i = 0; i < noOfActionOwner; i++) {
			int aoId = actionOwnerList.get(i);
			actionOwnerId[i] = aoId;
		}
		HashMap<Integer, Integer> mapRequestActionowner = new HashMap<Integer, Integer>();
		while (!executionQueue.isEmpty()) {
			for (int j = 0; j < noOfActionOwner && !executionQueue.isEmpty(); j++) {
				mapRequestActionowner.put(executionQueue.poll(), actionOwnerId[j]);
			}
		}
		return mapRequestActionowner;
	}
}