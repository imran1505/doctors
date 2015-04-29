package com.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.db.DAO;
import com.dto.Appointment;
import com.dto.Department;
import com.dto.Doctor;
import com.dto.Patient;

/**
 * Servlet implementation class JSPController
 */
public class JSPController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JSPController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handleAllRequest(request,response);
	}

	private void handleAllRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri= request.getRequestURL().toString();
		System.out.println("request URI:"+request.getRequestURI());
		String pageName = getActionNameFromURL(uri);
		DAO dao = new DAO();
		if("home".equals(pageName)){
			HttpSession session = request.getSession();
			String login = (String) session.getAttribute("login");
			if ("true".equals(login)) {
				getServletContext().getRequestDispatcher("/welcome.html").forward(request, response);
				return;
			}
			getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
		}else if("doctor".equals(pageName)){
			request.setAttribute("departments", Department.values());
			getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
		}else if("patient".equals(pageName)){
			request.setAttribute("departments", Department.values());
			getServletContext().getRequestDispatcher("/patient.jsp").forward(request, response);
		}else if("verify".equals(pageName)){
			String uid = request.getParameter("uid");
			String verificationCode = request.getParameter("v");
			Patient patient = dao.getPatientFromDbByVerificationCode(verificationCode);
			if(patient!=null && patient.getUsername()!=null){
				String generatedUid = ""+new BigInteger(patient.getUsername().getBytes());
				if(uid.equals(generatedUid)){
					HttpSession session=request.getSession();  
				    session.setAttribute("username", patient.getUsername());
				    session.setAttribute("name",patient.getFname()+ " "+patient.getLname());
					session.setAttribute("login","true");
					session.setAttribute("type","patient");
					dao.verifyPatient(patient.getUsername());
					getServletContext().getRequestDispatcher("/welcomep.jsp").forward(request, response);
					return;
				}
			}else{
				getServletContext().getRequestDispatcher("/404.jsp").forward(request, response);
				return;
			}
			
		}else if("recover".equals(pageName)){
			String code = request.getParameter("v");
			String uid = request.getParameter("uid");
			String type = request.getParameter("type");
			if(code!=null && uid!=null & type!=null){
				HttpSession session=request.getSession();  
			    session.setAttribute("uid", uid);
				session.setAttribute("type",type);
				session.setAttribute("code",code);
				
//				dao.verifyRecovery(username, code, newPassword);
				getServletContext().getRequestDispatcher("/newpwd.jsp").forward(request, response);
				return;
			}else{
				getServletContext().getRequestDispatcher("/recover.jsp").forward(request, response);
				return;
			}
			
		}else if("welcome".equals(pageName)){
			HttpSession session = request.getSession();
			String username = (String) session.getAttribute("username");
			String login = (String) session.getAttribute("login");
			String type = (String) session.getAttribute("type");
			
			if ("true".equals(login) && username != null) {
				if("doctor".equals(type)){
					Doctor doctor = dao.getDoctorFromDb(username);
					if("verified".equalsIgnoreCase(doctor.getStatus())){
						List<Appointment> confirmedAppointments= dao.getConfirmedAppointmentFromDbForDoctor(username, false, null);
						List<Appointment> pendingAppointments= dao.getPendingAppointmentFromDbForDoctor(username, false, null);
						List<Appointment> pastAppointments= dao.getPastAppointmentFromDbForDoctor(username, false, null);
						String name = (String) session.getAttribute("name");
						BigInteger uid = new BigInteger(username.getBytes());
						request.setAttribute("pendingAppointments", pendingAppointments);
						request.setAttribute("doctorName", name);
						request.setAttribute("uid", ""+uid);
						
						request.setAttribute("confirmedAppointments", confirmedAppointments);
						request.setAttribute("pastAppointments", pastAppointments);
						getServletContext().getRequestDispatcher("/welcome.jsp").forward(request, response);
						return;
					}else{
						getServletContext().getRequestDispatcher("/incompletedoctor.jsp").forward(request, response);
						return;
					}
					
					
				}else if("patient".equals(type)){
					Patient patient = dao.getPatientFromDb(username);
					if(patient == null){
						getServletContext().getRequestDispatcher("/404.jsp").forward(request, response);
					}
					if(!"verified".equalsIgnoreCase(patient.getVerificationCode())){
						getServletContext().getRequestDispatcher("/incomplete.jsp").forward(request, response);
						return;
					}
					String chatUid = request.getParameter("cuid");
					System.out.println("chatUid:"+chatUid);
					if(chatUid!=null && !chatUid.equals("")){
						request.setAttribute("cuid",chatUid);
					}else{
						request.setAttribute("cuid","");
					}
					getServletContext().getRequestDispatcher("/welcomep.jsp").forward(request, response);
					return;
				}
			}
			getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
			return;
		}else if("changePwd".equals(pageName)){
			//TODO make respective heading for doctor and patient e.g. doctors portal
			HttpSession session = request.getSession();
			String login = (String) session.getAttribute("login");
			String username = (String) session.getAttribute("username");
			String type = (String) session.getAttribute("type");
			System.out.println("username:"+username+" login:"+login+ " type:"+type);
			if ("true".equals(login)) {
				getServletContext().getRequestDispatcher("/changepwd.jsp").forward(request, response);
				return;
			}
			getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
			
		}else if("bookAppointment".equals(pageName)){
			//TODO make respective heading for doctor and patient e.g. doctors portal
			HttpSession session = request.getSession();
			String login = (String) session.getAttribute("login");
			String username = (String) session.getAttribute("username");
			String type = (String) session.getAttribute("type");
			String department = request.getParameter("department");
			System.out.println("apoointmnet bokking for:"+department);
			request.setAttribute("departments", Department.values());
			System.out.println("username:"+username+" login:"+login+ " type:"+type);
			if ("true".equals(login)) {
				if(department!=null && department!=""){
					
					List<Doctor> doctorList = dao.getDoctorBasedOnDepartment(Department.getDeparmentByName(department));
					request.setAttribute("department", department);
					request.setAttribute("doctors", doctorList);
					getServletContext().getRequestDispatcher("/bookDoctors.jsp").forward(request, response);
					return;

				}else{
					getServletContext().getRequestDispatcher("/bookAppointment.jsp").forward(request, response);
					return;
				}
			}
			getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);

		}else{
			getServletContext().getRequestDispatcher("/404.jsp").forward(request, response);
			return;
		}

	}
	
	private String getActionNameFromURL(String url){
		if(url==null || url.length()<1)
			return null;
		int index = url.lastIndexOf("/");
		int toIndex = url.length();
		if(url.contains("?"))
		{
			toIndex=url.indexOf("?")-1;
		}
		String action = url.substring(index+1, toIndex-5);
		return action;

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handleAllRequest(request,response);
	}

}
