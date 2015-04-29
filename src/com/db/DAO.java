package com.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.dto.Appointment;
import com.dto.Department;
import com.dto.Doctor;
import com.dto.Patient;
import com.utils.MailSender;

public class DAO {

	private DataSource dataSource;

	public DAO() {
		try {
			Context ctx = new InitialContext();
			dataSource = (DataSource) ctx
					.lookup("java:comp/env/jdbc/doctorsportal");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean checkLogin(String username, String password) {

		boolean isvalid = false;
		try {
			Connection con = dataSource.getConnection();
			String sql = "select count(*) from users where username=? and password=?";
			System.out.println("SQL");
			PreparedStatement pst = con.prepareStatement(sql);

			pst.setString(1, username);
			pst.setString(2, password);

			ResultSet rs = pst.executeQuery();
			System.out.println("st" + rs.getStatement());
			while (rs.next()) {
				System.out.println(rs.getInt(1));
				if (rs.getInt(1) == 1) {
					isvalid = true;
				}
			}
			con.close();
		} catch (SQLException e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}
		System.out.println("returing:" + isvalid);
		return isvalid;

	}
	

	public boolean checkPatientLogin(String username, String password) {

		boolean isvalid = false;
		try {
			Connection con = dataSource.getConnection();
			String sql = "select count(*) from patients where username=? and password=?";
			System.out.println("SQL");
			PreparedStatement pst = con.prepareStatement(sql);

			pst.setString(1, username);
			pst.setString(2, password);

			ResultSet rs = pst.executeQuery();
			System.out.println("st" + rs.getStatement());
			while (rs.next()) {
				System.out.println(rs.getInt(1));
				if (rs.getInt(1) == 1) {
					isvalid = true;
				}
			}
			con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		System.out.println("returing:" + isvalid);
		return isvalid;

	}

	public String doSignUp(String username, String password, String fname,
			String lname, String address, String city, String pincode,
			String state, String department) {

		String response = "Sign Up failed. Facing technical Difficulty.";
		try {
			Connection con = dataSource.getConnection();
			String sql = "select count(*) from doctors where username=?";
			PreparedStatement pst = con.prepareStatement(sql);

			pst.setString(1, username);
			ResultSet rs = pst.executeQuery();
			System.out.println("st" + rs.getStatement());
			while (rs.next()) {
				System.out.println(rs.getInt(1));
				if (rs.getInt(1) == 1) {
					response = "User already registered";
					return response;
				}
				// System.out.println("st"+rs.getStatement());
				// System.out.println("uname:"+rs.getString(1));
				// System.out.println("pwd:"+rs.getString(2));
			}
			sql = "insert into doctors values (?,?,?,?,?,?,?,?,?,?,?)";
			pst = con.prepareStatement(sql);

			pst.setString(1, username);
			pst.setString(2, password);
			pst.setString(3, fname);
			pst.setString(4, lname);
			pst.setString(5, address);
			pst.setString(6, city);
			pst.setString(7, pincode);
			pst.setString(8, state);
			pst.setString(9, "");
			pst.setString(10, "DOC_NOT_UPLOADED");
			pst.setString(11, department);
			int rownum = pst.executeUpdate();
			System.out.println("rownum:" + rownum);
			if (rownum == 1) {
				response = "valid";
				// sql="insert into IMAGE values (?,?)";
				// pst=con.prepareStatement(sql);
				// pst.setString(1, username);
				// pst.setString(2, "default.jpg");
				// rownum=pst.executeUpdate();
				// System.out.println("rownum after inserting imagepath value:"+rownum);
			}
			con.close();
		} catch (SQLException e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}
		System.out.println("returing:" + response);
		return response;

	}

	public Doctor getDoctorFromDb(String username, String password) {

		Doctor doctor = null;
		try {
			Connection con = dataSource.getConnection();
			String sql = "select fname,lname,address,city,pincode,state,registrationdocname,status, department from doctors where username=? and password=?";
			PreparedStatement pst = con.prepareStatement(sql);

			pst.setString(1, username);
			pst.setString(2, password);

			ResultSet rs = pst.executeQuery();
			System.out.println("st" + rs.getStatement());
			while (rs.next()) {
				doctor = new Doctor(username, rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));
			}
			con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return doctor;
	}
	
	public Doctor getDoctorFromDb(String username) {

		Doctor doctor = null;
		try {
			Connection con = dataSource.getConnection();
			String sql = "select fname,lname,address,city,pincode,state,registrationdocname,status, department from doctors where username=?";
			PreparedStatement pst = con.prepareStatement(sql);

			pst.setString(1, username);

			ResultSet rs = pst.executeQuery();
			System.out.println("st" + rs.getStatement());
			while (rs.next()) {
				doctor = new Doctor(username, rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));
			}
			con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return doctor;
	}
	
	public List<Doctor> getDoctorBasedOnDepartment(Department department) {

		List<Doctor> doctorsList = new ArrayList<>();
		try {
			Connection con = dataSource.getConnection();
			String sql = "select username,fname,lname,address,city,pincode,state,registrationdocname,status, department from doctors where department=?";
			PreparedStatement pst = con.prepareStatement(sql);

			pst.setString(1, department.getDepartmentName());
			ResultSet rs = pst.executeQuery();
			System.out.println("st" + rs.getStatement());
			while (rs.next()) {
				Doctor doctor = new Doctor( rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));
				doctorsList.add(doctor);
			}
			con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return doctorsList;
	}
	
	public List<Appointment> getPendingAppointmentFromDbForDoctor(String doctorid, boolean isConfirmed, Date date) {

		List<Appointment> appointments = new ArrayList<Appointment>();
		try {
			Connection con = dataSource.getConnection();
			String dateCompare = "=";
			if(date == null || date.equals("")){
				dateCompare = ">=";
			}
			String sql = "select * from appointments where status=? and doctorid = ? and appointmentdate"+dateCompare+" ? ";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, "pending");
			pst.setString(2, doctorid);
			java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());
			//TODO handling
			if(date == null || date.equals("")){
				pst.setDate(3,sqlDate );
			}else{
				pst.setDate(3,sqlDate);
			}
			
			ResultSet rs = pst.executeQuery();
			System.out.println("st" + rs.getStatement());
			while (rs.next()) {
				String patientId = rs.getString(2);
				String doctorId = rs.getString(3);
				Appointment appointment = new Appointment( rs.getString(1), patientId,doctorid, rs.getDate(4), rs.getString(5), rs.getString(6),rs.getString(7));
				Patient p = getPatientFromDb(patientId);
				Doctor d = getDoctorFromDb(doctorId);
				appointment.setPatientName(p.getName());
				appointment.setDoctorName(d.getName());
				appointments.add(appointment);
			}
			System.out.println(appointments);
			con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return appointments;
	}
	
	
	public List<String> getSlotsFromDbForDoctor(String doctorid, Date date) {

		List<String> slotList = new ArrayList<>();
		try {
			Connection con = dataSource.getConnection();
			String dateCompare = "=";
			if(date == null || date.equals("")){
				dateCompare = "=";
			}
			String sql = "select slot from appointments where doctorid = ? and appointmentdate"+dateCompare+" ? ";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, doctorid);
			java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());
			if(date == null || date.equals("")){
				pst.setDate(2,sqlDate );
			}else{
				pst.setDate(2,sqlDate);
			}
			
			ResultSet rs = pst.executeQuery();
			System.out.println("st" + rs.getStatement());
			while (rs.next()) {
				slotList.add(rs.getString(1));
			}
			System.out.println("not available slotlist for doctorid:"+doctorid+" date:"+date+" slots:"+slotList);
			con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return slotList;
	}
	
	public List<Appointment> getPastAppointmentFromDbForDoctor(String doctorid, boolean isConfirmed, Date date) {

		List<Appointment> appointments = new ArrayList<Appointment>();
		try {
			Connection con = dataSource.getConnection();
			String dateCompare = "=";
			if(date == null || date.equals("")){
				dateCompare = "<=";
			}
			String sql = "select * from appointments where doctorid = ? and appointmentdate"+dateCompare+" ? ";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, doctorid);
			java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());
			//TODO handling
			if(date == null || date.equals("")){
				pst.setDate(2,sqlDate );
			}else{
				pst.setDate(2,sqlDate);
			}
			
			ResultSet rs = pst.executeQuery();
			System.out.println("st" + rs.getStatement());
			while (rs.next()) {
				String patientId = rs.getString(2);
				String doctorId = rs.getString(3);
				Appointment appointment = new Appointment( rs.getString(1), patientId,doctorid, rs.getDate(4), rs.getString(5), rs.getString(6),rs.getString(7));
				Patient p = getPatientFromDb(patientId);
				Doctor d = getDoctorFromDb(doctorId);
				appointment.setPatientName(p.getName());
				appointment.setDoctorName(d.getName());
				appointments.add(appointment);
			}
			System.out.println(appointments);
			con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return appointments;
	}
	
	
	public List<Appointment> getConfirmedAppointmentFromDbForDoctor(String doctorid, boolean isConfirmed, Date date) {

		List<Appointment> appointments = new ArrayList<Appointment>();
		try {
			Connection con = dataSource.getConnection();
			String dateCompare = "=";
			if(date == null || date.equals("")){
				dateCompare = ">=";
			}
			String sql = "select * from appointments where status=? and doctorid = ? and appointmentdate"+dateCompare+" ? ";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, "confirm");
			pst.setString(2, doctorid);
			java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());
			//TODO handling
			if(date == null || date.equals("")){
				pst.setDate(3,sqlDate );
			}else{
				pst.setDate(3,sqlDate);
			}
			
			ResultSet rs = pst.executeQuery();
			System.out.println("st" + rs.getStatement());
			while (rs.next()) {
				String patientId = rs.getString(2);
				String doctorId = rs.getString(3);
				Appointment appointment = new Appointment( rs.getString(1), patientId,doctorid, rs.getDate(4), rs.getString(5), rs.getString(6),rs.getString(7));
				Patient p = getPatientFromDb(patientId);
				Doctor d = getDoctorFromDb(doctorId);
				appointment.setPatientName(p.getName());
				appointment.setDoctorName(d.getName());
				appointments.add(appointment);
			}
			System.out.println(appointments);
			con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return appointments;
	}
	
	public List<Appointment> getAppointmentFromDbForPatient(String patientId, boolean isConfirmed, Date date) {

		List<Appointment> appointments = new ArrayList<Appointment>();
		try {
			Connection con = dataSource.getConnection();
			String dateCompare = "=";
			if(date == null || date.equals("")){
				dateCompare = ">=";
			}
			String sql = "select * from appointments where status=? and patientid=? and appointmentdate"+dateCompare+" ? ";
			PreparedStatement pst = con.prepareStatement(sql);
			String todaysDate ="";
			pst.setString(1, "confirm");
			pst.setString(2, patientId);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if(date == null || date.equals("")){
				pst.setString(3, sdf.format(new Date()));
			}else{
				pst.setString(3, sdf.format(date));
			}
			

			ResultSet rs = pst.executeQuery();
			System.out.println("st" + rs.getStatement());
			while (rs.next()) {
				Appointment appointment = new Appointment( rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5), rs.getString(6),rs.getString(7));
				appointments.add(appointment);
			}
			con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return appointments;
	}
	
	
	
	public boolean approveAppointment(String appointmentId) {
		boolean response = false;
		try {
			Connection con = dataSource.getConnection();
			String sql = "update appointments set status = 'confirm' where id=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, appointmentId);
			int result = pst.executeUpdate();
			if(result == 1){
				response = true;
			}
			con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return response;
	}
	
	public boolean cancelAppointment(String appointmentId) {
		boolean response = false;
		try {
			Connection con = dataSource.getConnection();
			String sql = "update appointments set status = 'cancel' where id=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, appointmentId);
			int result = pst.executeUpdate();
			if(result == 1){
				response = true;
			}
			con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return response;
	}
	
	
	
	
	public boolean createAppointmentRequest(String doctorId, String patientId, String date,String department,String slot) {
		boolean response = false;
		try {
			Connection con = dataSource.getConnection();
			String sql = "insert into appointments values (UUID(),?,?,?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst = con.prepareStatement(sql);

			pst.setString(1, patientId);
			pst.setString(2, doctorId);
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Date d = sdf.parse(date);
			java.sql.Date sqlDate = new java.sql.Date(d.getTime());
			pst.setDate(3, sqlDate);
			pst.setString(4, "pending");
			pst.setString(5, department);
			pst.setString(6, slot);
			int rownum = pst.executeUpdate();
			System.out.println("rownum:" + rownum);
			if (rownum == 1) {
				response = true;
			}
			con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return response;
	}
	
	
	public Patient getPatientFromDb(String username, String password) {

		Patient patient = null;
		try {
			Connection con = dataSource.getConnection();
			String sql = "select fname,lname,verificationcode from patients where username=? and password=?";
			PreparedStatement pst = con.prepareStatement(sql);

			pst.setString(1, username);
			pst.setString(2, password);

			ResultSet rs = pst.executeQuery();
			System.out.println("st" + rs.getStatement());
			while (rs.next()) {
				patient = new Patient(username, rs.getString(1), rs.getString(2), rs.getString(3));
			}
			con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return patient;
	}
	
	
	public Patient getPatientFromDb(String username) {

		Patient patient = null;
		try {
			Connection con = dataSource.getConnection();
			String sql = "select fname,lname,verificationcode from patients where username=?";
			PreparedStatement pst = con.prepareStatement(sql);

			pst.setString(1, username);

			ResultSet rs = pst.executeQuery();
			System.out.println("st" + rs.getStatement());
			while (rs.next()) {
				patient = new Patient(username, rs.getString(1), rs.getString(2), rs.getString(3));
			}
			con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return patient;
	}
	
	
	public Patient getPatientFromDbByVerificationCode(String verificationCode) {

		Patient patient = null;
		try {
			Connection con = dataSource.getConnection();
			String sql = "select username,fname,lname,verificationcode from patients where verificationcode=?";
			PreparedStatement pst = con.prepareStatement(sql);

			pst.setString(1, verificationCode);

			ResultSet rs = pst.executeQuery();
			System.out.println("st" + rs.getStatement());
			while (rs.next()) {
				patient = new Patient(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
			}
			con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return patient;
	}
	
	
	public boolean verifyPatient(String username) {

		boolean isInserted = false;

		try {
			Connection con = dataSource.getConnection();
			String sql = "update patients set verificationcode=?  where username=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, "verified");
			pst.setString(2, username);
			int rowcount = pst.executeUpdate();
			if (rowcount == 1) {
				isInserted = true;
				System.out.println("Patient verified successfully");
			}
			con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		System.out.println("returing from verifyPatient:" + isInserted);
		return isInserted;
	}
	
	
	public boolean insertRecovery(String username) {

		boolean isInserted = false;

		try {
			Connection con = dataSource.getConnection();
			String sql = "update recovery set verificationcode=?  where username=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, "verified");
			pst.setString(2, username);
			int rowcount = pst.executeUpdate();
			if (rowcount == 1) {
				isInserted = true;
				System.out.println("Patient verified successfully");
			}
			con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		System.out.println("returing from verifyPatient:" + isInserted);
		return isInserted;
	}
	
	
	public String getPatientName(String uname, String pwd) {

		String name = null;
		try {
			Connection con = dataSource.getConnection();
			String sql = "select fname,lname,verificationcode from patients where username=? and password=?";
			PreparedStatement pst = con.prepareStatement(sql);

			pst.setString(1, uname);
			pst.setString(2, pwd);

			ResultSet rs = pst.executeQuery();
			System.out.println("st" + rs.getStatement());
			while (rs.next()) {
				System.out.println("Got Name:" + rs.getString(1) + " "
						+ rs.getString(2));
				name = rs.getString(1) + " " + rs.getString(2);
			}
			con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return name;
	}

	public String recover(String email, String type) {

		String response = "Facing technical difficulty.Please try later.";
		String randomCode = null;
		try {
			Connection con = dataSource.getConnection();
			String sql = "select count(*) from doctors where username=?";
			if("patient".equalsIgnoreCase(type)){
				sql = "select count(*) from patients where username=?";
			}
			
			PreparedStatement pst = con.prepareStatement(sql);

			pst.setString(1, email);
			ResultSet rs = pst.executeQuery();
			System.out.println("st" + rs.getStatement());
			while (rs.next()) {
				System.out.println(rs.getInt(1));
				if (rs.getInt(1) == 1) {
					System.out.println("user found. sending email to his registered email address:" + email);
					randomCode = MailSender.sendRecoveryMail(email,type);
					System.out.println("random code genearted:" + randomCode);
					if (randomCode == null) {
						response = "This email is not registered with us. Please enter registered email address.";
						return response;
					}
					if (randomCode.equals("ERROR")) {
						return response;
					}
				} else {
					response = "This email is not registered with us. Please enter registered email address.";
					return response;
				}
			}
			sql = "insert into recovery values (UUID(),?,?,?)";
			pst = con.prepareStatement(sql);

			pst.setString(1, email);
			pst.setString(2, "0");
			pst.setString(3, randomCode);
			int rownum = pst.executeUpdate();
			System.out.println("rownum:" + rownum);
			if (rownum == 1) {
				response = "sent";
			}
			con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		System.out.println("returing:" + response);
		return response;

	}

	public boolean verifyRecovery(String username, String code, String newPassword, String type) {

		boolean response = false;
		try {
			Connection con = dataSource.getConnection();
			String sql = "select count(*) from recovery where username=? and code=? and status='0'";
			PreparedStatement pst = con.prepareStatement(sql);

			pst.setString(1, username);
			pst.setString(2, code);
			ResultSet rs = pst.executeQuery();
			System.out.println("st" + rs.getStatement());
			while (rs.next()) {
				// System.out.println(rs.getInt(1));
				if (rs.getInt(1) != 1) {
					return response;
				} else {
					sql = "update recovery set status='1' where username=? and code=?";
					pst = con.prepareStatement(sql);
					pst.setString(1, username);
					pst.setString(2, code);
					System.out.println(" SQL:" + sql);
					int rowcount = pst.executeUpdate();
					if (rowcount == 1) {
						sql = "update patients set password=? where username=?";
						if(type.equals("doctor")){
							sql = "update doctors set password=? where username=?";
						}
						pst = con.prepareStatement(sql);
						pst.setString(1, newPassword);
						pst.setString(2, username);
						System.out.println("password:" + newPassword);
						rowcount = pst.executeUpdate();
						if (rowcount == 1) {
							response = true;
							return response;
						}

					}
					break;
				}
			}
			con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		System.out.println("returing:" + response);
		return response;
	}

	public String changePwd(String username, String oldpwd, String newPwd, String type) {
		String isPassowrdChanged = "Facing technical Difficulty.";

		try {
			Connection con = dataSource.getConnection();
			String sql = "select count(*) from patients where username=? and password=?";
			if(type.equals("doctor")){
				 sql = "select count(*) from doctors where username=? and password=?";
			}
			PreparedStatement pst = con.prepareStatement(sql);

			pst.setString(1, username);
			pst.setString(2, oldpwd);
			ResultSet rs = pst.executeQuery();
			System.out.println("st" + rs.getStatement());
			while (rs.next()) {
				if (rs.getInt(1) != 1) {
					isPassowrdChanged = "Your password do not match with what we have in our database.";
					return isPassowrdChanged;
				} else {
					sql = "update patients set PASSWORD=? where username=?";
					if(type.equals("doctor")){
						sql = "update doctors set PASSWORD=? where username=?";
					}
					pst = con.prepareStatement(sql);
					pst.setString(1, newPwd);
					pst.setString(2, username);
					System.out.println(" SQL:" + sql);
					int rowcount = pst.executeUpdate();
					if (rowcount == 1) {
						isPassowrdChanged = "true";
						return isPassowrdChanged;

					}
					break;
				}
			}
			con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		System.out.println("returing:" + isPassowrdChanged);
		return isPassowrdChanged;
	}

	public boolean updateDocFileName(String username, String globalFileName) {

		boolean isInserted = false;

		try {
			Connection con = dataSource.getConnection();
			String sql = "update doctors  set registrationdocname=?  where username=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, globalFileName);
			pst.setString(2, username);
			int rowcount = pst.executeUpdate();
			if (rowcount == 1) {
				isInserted = true;
				System.out.println("doc path updated successfully.");
			}
			con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		System.out.println("returing from updatedoc:" + isInserted);
		return isInserted;

	}

	public String doPatientSignUp(String username, String password,
			String fname, String lname) {

		String response = "Sign Up failed. Facing technical Difficulty.";
		try {
			Connection con = dataSource.getConnection();
			String sql = "select count(*) from patients where username=?";
			PreparedStatement pst = con.prepareStatement(sql);

			pst.setString(1, username);
			ResultSet rs = pst.executeQuery();
			System.out.println("st" + rs.getStatement());
			while (rs.next()) {
				System.out.println(rs.getInt(1));
				if (rs.getInt(1) == 1) {
					response = "User already registered";
					return response;
				}
			}
			String randomCode = sendVerificationMail(username);
			sql = "insert into patients values (?,?,?,?,?)";
			pst = con.prepareStatement(sql);

			pst.setString(1, username);
			pst.setString(2, password);
			pst.setString(3, fname);
			pst.setString(4, lname);
			pst.setString(5, randomCode);
			int rownum = pst.executeUpdate();
			System.out.println("rownum:" + rownum);
			if (rownum == 1) {
				response = "registered";
			}
			con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		System.out.println("returing:" + response);
		return response;

	}

	public String sendVerificationMail(String email) {

		String randomCode = null;
		randomCode = MailSender.sendVerificationMail(email);
		return randomCode;
	}

}
