package com.dto;

public class Patient {

	private String username;
	private String fname;
	private String lname;
	private String verificationCode;
	
	
	public Patient(String username, String fname, String lname,
			String verificationCode) {
		super();
		this.username = username;
		this.fname = fname;
		this.lname = lname;
		this.verificationCode = verificationCode;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getVerificationCode() {
		return verificationCode;
	}
	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	@Override
	public String toString() {
		return "Patient [username=" + username + ", fname=" + fname
				+ ", lname=" + lname + ", verificationCode=" + verificationCode
				+ ", getUsername()=" + getUsername() + ", getFname()="
				+ getFname() + ", getLname()=" + getLname()
				+ ", getVerificationCode()=" + getVerificationCode()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
	
	
}
