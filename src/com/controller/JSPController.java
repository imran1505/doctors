package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.Department;

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
		System.out.println("uri:"+uri);
		String action = getActionNameFromURL(uri);
		if("welcome".equals(action)){
			System.out.println("request URI:"+request.getRequestURI());
			request.setAttribute("result", "12345");
			getServletContext().getRequestDispatcher("/welcome.jsp").forward(request, response);
		}else if("doctor".equals(action)){
			System.out.println("request URI:"+request.getRequestURI());
			request.setAttribute("departments", Department.values());
			getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
		}

	}
	
	private String getActionNameFromURL(String url){
		if(url==null || url.length()<1)
			return null;
		int index = url.lastIndexOf("/");
		String action = url.substring(index+1, url.length());
		return action;

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handleAllRequest(request,response);
	}

}
