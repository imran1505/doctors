package com.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

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
			String state) {

		String response = "Sign Up failed. Facing technical Difficulty.";
		try {
			Connection con = dataSource.getConnection();
			String sql = "select count(*) from users where username=?";
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
			sql = "insert into users values (?,?,?,?,?,?,?,?,?,?)";
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
			String sql = "select fname,lname,address,city,pincode,state,registrationdocname,status from doctors where username=? and password=?";
			PreparedStatement pst = con.prepareStatement(sql);

			pst.setString(1, username);
			pst.setString(2, password);

			ResultSet rs = pst.executeQuery();
			System.out.println("st" + rs.getStatement());
			while (rs.next()) {
				doctor = new Doctor(username, rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
			}
			con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return doctor;
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

	public String recover(String email) {

		String response = "Facing technical difficulty.Please try later.";
		String randomCode = null;
		try {
			Connection con = dataSource.getConnection();
			String sql = "select count(*) from users where username=?";
			PreparedStatement pst = con.prepareStatement(sql);

			pst.setString(1, email);
			ResultSet rs = pst.executeQuery();
			System.out.println("st" + rs.getStatement());
			while (rs.next()) {
				System.out.println(rs.getInt(1));
				if (rs.getInt(1) == 1) {
					System.out
							.println("user found. sending email to his registered email address:"
									+ email);
					randomCode = MailSender.sendRecoveryMail(email);
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
			sql = "insert into pwdrecovery values (UUID(),?,?,?)";
			pst = con.prepareStatement(sql);

			pst.setString(1, email);
			pst.setString(2, "0");
			pst.setString(3, randomCode);
			int rownum = pst.executeUpdate();
			System.out.println("rownum:" + rownum);
			if (rownum == 1) {
				response = "valid";
			}
			con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		System.out.println("returing:" + response);
		return response;

	}

	public boolean verify(String username, String code, String newPassword) {

		boolean response = false;
		try {
			Connection con = dataSource.getConnection();
			String sql = "select count(*) from PWDRECOVERY where username=? and verificationcode=? and VERIFICATIONSTATUS='0'";
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
					sql = "update PWDRECOVERY set VERIFICATIONSTATUS='1' where username=? and verificationcode=?";
					pst = con.prepareStatement(sql);
					pst.setString(1, username);
					pst.setString(2, code);
					System.out.println(" SQL:" + sql);
					int rowcount = pst.executeUpdate();
					if (rowcount == 1) {
						sql = "update USERS set PASSWORD=? where username=?";
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

	public String changePwd(String username, String oldpwd, String newPwd) {
		String isPassowrdChanged = "Facing technical Difficulty.";

		try {
			Connection con = dataSource.getConnection();
			String sql = "select count(*) from USERS where username=? and password=?";
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
					sql = "update USERS set PASSWORD=? where username=?";
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
				response = "valid";
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
