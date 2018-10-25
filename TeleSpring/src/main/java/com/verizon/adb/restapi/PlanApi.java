package com.verizon.adb.restapi;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.verizon.adb.model.Plan;
import com.verizon.adb.service.PlanService;

@RestController
@CrossOrigin
@RequestMapping("/plans")
public class PlanApi {

	@Autowired
	private PlanService service;

	@GetMapping
	public ResponseEntity<List<Plan>> getAllPlans() {
		return new ResponseEntity<>(service.getAllPlans(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Plan> getPlanById(@PathVariable("id") long planId) {
		ResponseEntity<Plan> resp;
		Plan c = service.getPlanById(planId);
		if (c == null)
			resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			resp = new ResponseEntity<>(c, HttpStatus.OK);
		return resp;
	}

	@GetMapping("/{field}/{srhValue}")
	public ResponseEntity<List<Plan>> getAllPlans(@PathVariable("field") String fieldBy,
			@PathVariable("srhValue") String searchValue) {

		ResponseEntity<List<Plan>> resp;

		switch (fieldBy) {
		case "mobileNumber":
			Plan cByMobNum = service.findByMobileNumber(searchValue);
			if (cByMobNum != null) {
				resp = new ResponseEntity<>(Collections.singletonList(cByMobNum), HttpStatus.OK);
			} else {
				resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			break;
		case "emailId":
			Plan cByEmail = service.findByEmailId(searchValue);
			if (cByEmail != null) {
				resp = new ResponseEntity<>(Collections.singletonList(cByEmail), HttpStatus.OK);
			} else {
				resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			break;
		case "lastName":
			List<Plan> results = service.findAllByLastName(searchValue);
			if (results != null && results.size() != 0) {
				resp = new ResponseEntity<>(results, HttpStatus.OK);
			} else {
				resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			break;
		default:
			resp = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			break;
		}

		return resp;
	}

	@PostMapping
	public ResponseEntity<Plan> addPlan(@RequestBody Plan plan) {
		ResponseEntity<Plan> resp = null;

		if (service.existsByEmailId(plan.getEmailId())) {
			resp = new ResponseEntity<Plan>(HttpStatus.ALREADY_REPORTED);
		}

		if (service.existsByMobileNumber(plan.getMobileNumber())) {
			resp = new ResponseEntity<Plan>(HttpStatus.ALREADY_REPORTED);
		}

		if (resp == null) {
			Plan c = service.addPlan(plan);
			if (c == null)
				resp = new ResponseEntity<Plan>(HttpStatus.BAD_REQUEST);
			else
				resp = new ResponseEntity<Plan>(c, HttpStatus.OK);
		}
		return resp;
	}

	@PutMapping
	public ResponseEntity<Plan> updatePlan(@RequestBody Plan plan) {
		ResponseEntity<Plan> resp = null;

		Plan c = service.getPlanById(plan.getPlanId());
		if (!plan.getEmailId().equals(c.getEmailId())) {
			if (service.existsByEmailId(plan.getEmailId())) {
				resp = new ResponseEntity<Plan>(HttpStatus.ALREADY_REPORTED);
			}
		}

		if (!plan.getMobileNumber().equals(c.getMobileNumber())) {
			if (service.existsByMobileNumber(plan.getMobileNumber())) {
				resp = new ResponseEntity<Plan>(HttpStatus.ALREADY_REPORTED);
			}
		}

		if (resp == null) {
			c = service.updatePlan(plan);
			if (c == null)
				resp = new ResponseEntity<Plan>(HttpStatus.BAD_REQUEST);
			else
				resp = new ResponseEntity<Plan>(c, HttpStatus.OK);
		}
		return resp;
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePlan(@PathVariable("id") long planId) {
		ResponseEntity<Void> resp = null;

		if (service.deletePlan(planId))
			resp = new ResponseEntity<>(HttpStatus.OK);
		else
			resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);

		return resp;
	}
}
