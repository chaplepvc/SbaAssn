package com.verizon.adb.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.verizon.adb.model.Plan;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long>{

	boolean existsByMobileNumber(String mobileNumber);
	boolean existsByEmailId(String emailId);
	
	Plan findByMobileNumber(String mobileNumber);
	Plan findByEmailId(String emailId);
	
	List<Plan> findAllByLastName(String lastName);
	
}
