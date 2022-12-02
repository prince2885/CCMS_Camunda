package com.camunda.example.CCMS;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class fluxdata {
	
	
	
	
	
	@Id
	private String name;
	private int age;
	private String courtid;
	private String adharno;
	private String email;
	private String checkercomment;
	private String makercomment;
	
	
	
	
	public String getCheckercomment() {
		return checkercomment;
	}
	public void setCheckercomment(String checkercomment) {
		this.checkercomment = checkercomment;
	}
	public String getMakercomment() {
		return makercomment;
	}
	public void setMakercomment(String makercomment) {
		this.makercomment = makercomment;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getCourtid() {
		return courtid;
	}
	public void setCourtid(String courtid) {
		this.courtid = courtid;
	}
	public String getAdharno() {
		return adharno;
	}
	public void setAdharno(String adharno) {
		this.adharno = adharno;
	}
	
	
	

}
