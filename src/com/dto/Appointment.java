package com.dto;

import java.sql.Date;

public class Appointment {

	private int id;
	private String patientid;
	private String doctorid;
	private Date appointmentdate;
	private boolean status;
	private String department;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPatientid() {
		return patientid;
	}

	public void setPatientid(String patientid) {
		this.patientid = patientid;
	}

	public String getDoctorid() {
		return doctorid;
	}

	public void setDoctorid(String doctorid) {
		this.doctorid = doctorid;
	}

	public Date getAppointmentdate() {
		return appointmentdate;
	}

	public void setAppointmentdate(Date appointmentdate) {
		this.appointmentdate = appointmentdate;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
	

	public Appointment(int id, String patientid, String doctorid,
			Date appointmentdate, boolean status, String department) {
		super();
		this.id = id;
		this.patientid = patientid;
		this.doctorid = doctorid;
		this.appointmentdate = appointmentdate;
		this.status = status;
		this.department = department;
	}

	@Override
	public String toString() {
		return "Appointment [id=" + id + ", patientid=" + patientid
				+ ", doctorid=" + doctorid + ", appointmentdate="
				+ appointmentdate + ", status=" + status + ", department="
				+ department + "]";
	}
	
	

}
