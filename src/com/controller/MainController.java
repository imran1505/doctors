package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.db.DAO;
import com.dto.Doctor;
import com.dto.Patient;
import com.utils.DocumentUploader;



public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MainController() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("In controller doGet");
		try{
			handleAllRequest(request, response);
		}catch (Throwable  t) {
			RequestDispatcher dispatcher=request.getRequestDispatcher("/Error.jsp");
			dispatcher.forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("In controller doPost");
		try{
			handleAllRequest(request, response);
		}catch (Throwable  t) {
			RequestDispatcher dispatcher=request.getRequestDispatcher("/Error.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	private void handleAllRequest ( HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("In controller handleAllRequest");
		boolean checkSession = Boolean.parseBoolean(request.getParameter("ssnChk"));
		HttpSession session=request.getSession();
		System.out.println("Request URI:"+request.getRequestURI());
		
		if( (session.isNew() || session.getAttribute("username")==null) && checkSession){
			System.out.println("Checking User Session");
			RequestDispatcher dispatcher=request.getRequestDispatcher("/home.jsp");
			dispatcher.forward(request, response);
		}
		
		String action=request.getParameter("action");
		
		if(action!=null && action.trim().length()!=0){
			if("login".equals(action)){
				doLogin(request,response);
			}
			if("logout".equals(action)){
				request.getSession().invalidate();
				System.out.println(request.getServerPort());
				System.out.println(request.getServerName());
				System.out.println(request.getServletPath());
				response.sendRedirect("home.jsp");
			}
//			if("plogin".equals(action)){
//				doPatientLogin(request, response);
//			}
//			if("plogout".equals(action)){
//				request.getSession().invalidate();
//				System.out.println(request.getServerPort());
//				System.out.println(request.getServerName());
//				System.out.println(request.getServletPath());
//				response.sendRedirect("phome.jsp");
//			}
			if("signup".equals(action)){
				doSignUp(request,response);
			}
			if("patientsignup".equals(action)){
				doPatientSignUp(request, response);
			}
			if("chpwd".equals(action)){
				changePwd(request,response);
			}
			if("upload".equalsIgnoreCase(action)){
				upload(request,response);
			}
		}else{
			
		}
	}


	private void changePwd(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		String oldpwd= request.getParameter("pwd");
		String newPwd= request.getParameter("newpwd");
		String username = (String)request.getSession().getAttribute("username");
		DAO dao= new DAO();
		PrintWriter pw= response.getWriter();
		String isChanged="Facing Technical difficulty. Please try again.";
		if(username==null || oldpwd ==null || newPwd==null)
		{
		}else{
			isChanged=dao.changePwd(username,oldpwd,newPwd);
		}
		System.out.println("retuning:"+isChanged);
		if(isChanged.equalsIgnoreCase("true")){
			request.getSession().invalidate();
		}
		pw.print(isChanged);
	}



	private void doLogin(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		String type=request.getParameter("type");
		System.out.println(" login for type:"+type);
		
		if("doctor".equalsIgnoreCase(type)){
			doDoctorsLogin(request, response);
			return;
		}
		
		if("patient".equalsIgnoreCase(type)){
			doPatientLogin(request, response);
			return;
		}
	}
	
	private void doDoctorsLogin(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		DAO dao = new DAO();
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		System.out.println("Username: "+username+ " Password: "+password);
		Doctor doctor = dao.getDoctorFromDb(username,password);
		PrintWriter pw =response.getWriter();
		if(doctor != null){
			System.out.println("For username:"+username+" and password:"+password+ " found doctor:"+doctor.toString());
			HttpSession session=request.getSession();  
		    session.setAttribute("username", username);
		    session.setAttribute("name", "Dr. " +doctor.getFname()+ " "+doctor.getLname());
			session.setAttribute("login","true");
			session.setAttribute("type","doctor");
			session.setAttribute("docPath", doctor.getRegistrationDocName());
			pw.print("valid");
		}
		else{
			pw.print("invalid");
		}
	}
	
	
	private void doPatientLogin(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		DAO dao = new DAO();
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		System.out.println("Username: "+username+ " Password: "+password);
		Patient patient = dao.getPatientFromDb(username,password);
		PrintWriter pw =response.getWriter();
		if(patient != null){
			System.out.println("For username:"+username+" and password:"+password+ " found patient:"+patient.toString());
			HttpSession session=request.getSession();  
		    session.setAttribute("username", username);
		    session.setAttribute("name",patient.getFname()+ " "+patient.getLname());
			session.setAttribute("login","true");
			session.setAttribute("type","patient");
			pw.print("valid");
		}
		else{
			pw.print("invalid");
		}
	}
	
	
	private void doSignUp(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		String uname=request.getParameter("username");
		System.out.println("username recieved:"+uname);
		
		String pwd=request.getParameter("password");
		System.out.println("password recieved:"+pwd);
		
		String fname=request.getParameter("fname");
		String lname=request.getParameter("lname");
		String address=request.getParameter("address");
		String city=request.getParameter("city");
		String pincode=request.getParameter("pincode");
		String state=request.getParameter("state");
		
		DAO dao=new DAO();
		String resStr=dao.doSignUp(uname, pwd,fname,lname,address,city,pincode,state);
		PrintWriter pw =response.getWriter();
		HttpSession session=request.getSession();  
	    session.setAttribute("username", uname);
	    session.setAttribute("name", fname+" "+lname);
		if(resStr.equals("valid")){
			session.setAttribute("login","true");
			session.setAttribute("imagePath","default.jpg");
		}
		pw.print(resStr);
		
	}
	
	private void doPatientSignUp(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		String uname=request.getParameter("username");
		System.out.println("username recieved:"+uname);
		
		String pwd=request.getParameter("password");
		System.out.println("password recieved:"+pwd);
		
		String fname=request.getParameter("fname");
		String lname=request.getParameter("lname");
		
		DAO dao=new DAO();
		String resStr=dao.doPatientSignUp(uname, pwd,fname,lname);
		PrintWriter pw =response.getWriter();
		HttpSession session=request.getSession();  
	    session.setAttribute("username", uname);
	    session.setAttribute("name", fname+" "+lname);
		if(resStr.equals("valid")){
			session.setAttribute("login","true");
			session.setAttribute("imagePath","default.jpg");
		}
		pw.print(resStr);
		
	}
	
	private void upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String resStr=DocumentUploader.uploadDoc(request, response);
		System.out.println("Response:"+resStr);
		HttpSession session=request.getSession();
		session.setAttribute("docPath",resStr);
		PrintWriter pw=response.getWriter();
		pw.print(resStr);
	}
	
//	private void xyz(){
//		File file = new File(filePath);
//        int length   = 0;
//        ServletOutputStream outStream = response.getOutputStream();
//        ServletContext context  = getServletConfig().getServletContext();
//        String mimetype = context.getMimeType(filePath);
//        
//        // sets response content type
//        if (mimetype == null) {
//            mimetype = "application/octet-stream";
//        }
//        response.setContentType(mimetype);
//        response.setContentLength((int)file.length());
//        String fileName = (new File(filePath)).getName();
//        
//        // sets HTTP header
//        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
//        
//        byte[] byteBuffer = new byte[BUFSIZE];
//        DataInputStream in = new DataInputStream(new FileInputStream(file));
//        
//        // reads the file's bytes and writes them to the response stream
//        while ((in != null) && ((length = in.read(byteBuffer)) != -1))
//        {
//            outStream.write(byteBuffer,0,length);
//        }
//        
//        in.close();
//        outStream.close();
//	}
	
	
	

}
