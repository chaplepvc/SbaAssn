package com.verizon.adb.service;

import java.util.List;

import com.verizon.adb.model.Plan;

public interface PlanService {

	Plan getPlanById(long planId);
	List<Plan> getAllPlans();
	Plan addPlan(Plan plan);
	Plan updatePlan(Plan plan);
	boolean deletePlan(long planId);
	
	boolean existsByMobileNumber(String mobileNumber);
	boolean existsByEmailId(String emailId);
	
	Plan findByMobileNumber(String mobileNumber);
	Plan findByEmailId(String emailId);
	
	List<Plan> findAllByLastName(String lastName);
	
}
