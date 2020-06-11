package com.apidesign.customerapidemo2.models;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;


@Entity
@Table(name="Virtusa_Customer")
@XmlType
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Customer_Id")
	private long customerId;
	@Column(name="Name",nullable= false,length = 70)
	private String name;
	@Column(name="Email",nullable= false,length = 100) 
	private String email;
	
	@Column(name="Mobile_No")
	private long mobileNo;
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name="Dob")
	private LocalDate dob;

	public long getCustomerId() {
		return customerId;
	}
public void setCustomerId(long customerId) {
	this.customerId=customerId;
}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	
	
	
	
	
}