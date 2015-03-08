package com.dto;

public class Doctor {

	private String username;
	private String fname;
	private String lname;
	private String address;
	private String city;
	private String pincode;
	private String state;
	private String registrationDocName;
	private String status;
	private Department department;
	
	public Doctor(String username, String fname, String lname, String address,
			String city, String pincode, String state,
			String registrationDocName, String status, String departmentName) {
		super();
		this.username = username;
		this.fname = fname;
		this.lname = lname;
		this.address = address;
		this.city = city;
		this.pincode = pincode;
		this.state = state;
		this.registrationDocName = registrationDocName;
		this.status = status;
		setDepartment(departmentName);
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getRegistrationDocName() {
		return registrationDocName;
	}

	public void setRegistrationDocName(String registrationDocName) {
		this.registrationDocName = registrationDocName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public void setDepartment(String departmentName) {
		this.department = Department.getDeparmentByName(departmentName);
	}

	@Override
	public String toString() {
		return "Doctor [username=" + username + ", fname=" + fname + ", lname="
				+ lname + ", address=" + address + ", city=" + city
				+ ", pincode=" + pincode + ", state=" + state
				+ ", registrationDocName=" + registrationDocName + ", status="
				+ status + ", department=" + department + "]";
	}

}