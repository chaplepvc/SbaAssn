package com.verizon.adb.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.junit.runners.Parameterized.Parameter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name="plans")
public class Plan {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long planId;
	
	@NotEmpty(message="firstName can not be empty")
	@Size(min=5,max=15,message="firstName must be of 5 to 15 chars")
	private String firstName;
	
	@NotEmpty(message="lastName can not be empty")
	@Size(min=5,max=15,message="lastName must be of 5 to 15 chars")
	private String lastName;
	
	@DateTimeFormat(iso=ISO.DATE)
	@NotNull(message="Date Of Birth can not be left blank")
	@Column(name="dob")
	private LocalDate dateOfBirth;
	
	@NotEmpty(message="Email Id can not be null")
	@Email(message="Invalid Email Id")
	private String emailId;
	
	@NotEmpty(message="Mobile Number can not be null")
	@Pattern(regexp="\\d{10}",message="mobile number can be ony 10 digits")
	@Column(name="mno")
	private String mobileNumber;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	private String timeSlot;
	
	public long getPackageId() {
		return planId;
	}
	public void setPackageId(long planId) {
		this.planId = planId;
	}
	public String getTimeSlot() {
		return timeSlot;
	}
	public void setTimeSlot(String timeSlot) {
		this.timeSlot = timeSlot;
	}
	public long getPlanId() {
		return planId;
	}
	public void setPlanId(long planId) {
		this.planId = planId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	
}
