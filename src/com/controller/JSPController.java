package com.controller;

import java.io.IOException;
import java.math.BigInteger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.db.DAO;
import com.dto.Department;
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
			DAO dao = new DAO();
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
					getServletContext().getRequestDispatcher("/welcome.jsp").forward(request, response);
					return;
				}else if("patient".equals(type)){
					DAO dao = new DAO();
					Patient patient = dao.getPatientFromDb(username);
					if(patient == null){
						getServletContext().getRequestDispatcher("/404.jsp").forward(request, response);
					}
					if(!"verified".equalsIgnoreCase(patient.getVerificationCode())){
						getServletContext().getRequestDispatcher("/incomplete.jsp").forward(request, response);
						return;
					}
					getServletContext().getRequestDispatcher("/welcomep.jsp").forward(request, response);
					return;
				}
			}
			getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
			return;
		}else if("changePwd".equals(pageName)){
			
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
			
		}
		else{
			getServletContext().getRequestDispatcher("/404.jsp").forward(request, response);
			return;
		}

	}
	
	private String getActionNameFromURL(String url){
		if(url==null || url.length()<1)
			return null;
		int index = url.lastIndexOf("/");
		String action = url.substring(index+1, url.length()-5);
		return action;

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handleAllRequest(request,response);
	}

}
