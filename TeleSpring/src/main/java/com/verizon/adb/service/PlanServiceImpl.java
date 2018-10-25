package com.verizon.adb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verizon.adb.dao.PlanRepository;
import com.verizon.adb.model.Plan;

@Service
public  class PlanServiceImpl implements PlanService {
	
	@Autowired
	private PlanRepository planRepo;

	@Override
	public Plan getPlanById(long planId) {
		Plan plan=null;
		
		/*if(planRepo.existsById(planId)) {
			plan = planRepo.findById(planId).get();
		}*/
		
		Optional<Plan> optPlan =planRepo.findById(planId);
		if(optPlan.isPresent()) {
			plan = optPlan.get();
		}
		
		return plan;
	}

	@Override
	public List<Plan> getAllPlans() {
		return planRepo.findAll();
	}

	@Override
	public Plan addPlan(Plan plan) {
		return planRepo.save(plan);
	}

	@Override
	public Plan updatePlan(Plan plan) {
		return planRepo.save(plan);
	}

	@Override
	public boolean deletePlan(long planId) {
		boolean isDeleted=false;
		if(planRepo.existsById(planId)) {
			planRepo.deleteById(planId);
			isDeleted=true;
		}
		return isDeleted;
	}

	@Override
	public boolean existsByMobileNumber(String mobileNumber) {
		return planRepo.existsByMobileNumber(mobileNumber);
	}

	@Override
	public boolean existsByEmailId(String emailId) {
		return planRepo.existsByEmailId(emailId);
	}

	@Override
	public Plan findByMobileNumber(String mobileNumber) {
		return planRepo.findByMobileNumber(mobileNumber);
	}

	@Override
	public Plan findByEmailId(String emailId) {
		return planRepo.findByEmailId(emailId);
	}

	@Override
	public List<Plan> findAllByLastName(String lastName) {
		return planRepo.findAllByLastName(lastName);
	}

}
