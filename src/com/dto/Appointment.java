package com.dto;

import java.sql.Date;

public class Appointment {

	private String id;
	private String patientid;
	private String doctorid;
	private Date appointmentdate;
	private String status;
	private String department;
    private String doctorName;
    private String patientName;
    private String slot;

	public String getId() {
		return id;
	}

	public void setId(String id) {
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


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
	

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public Appointment(String id, String patientid, String doctorid,
			Date appointmentdate, String status, String department, String slot) {
		super();
		this.id = id;
		this.patientid = patientid;
		this.doctorid = doctorid;
		this.appointmentdate = appointmentdate;
		this.status = status;
		this.department = department;
		this.slot = slot;
	}

	public String getSlot() {
		return slot;
	}

	public void setSlot(String slot) {
		this.slot = slot;
	}

	@Override
	public String toString() {
		return "Appointment [id=" + id + ", patientid=" + patientid
				+ ", doctorid=" + doctorid + ", appointmentdate="
				+ appointmentdate + ", status=" + status + ", department="
				+ department + ", doctorName=" + doctorName + ", patientName="
				+ patientName + ", slot=" + slot + "]";
	}

	
	
	

}
