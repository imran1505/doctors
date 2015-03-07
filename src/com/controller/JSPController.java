package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.stub.java.rmi._Remote_Stub;

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
		System.out.println("request URI:"+request.getRequestURI());
		request.setAttribute("result", "12345");
		getServletContext().getRequestDispatcher("/welcome.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handleAllRequest(request,response);
	}

}
