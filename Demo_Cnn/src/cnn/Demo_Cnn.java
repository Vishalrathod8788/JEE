package cnn;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Demo_Cnn")
public class Demo_Cnn extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
		Connection cnn;
		Statement stmt;
		String Qry, Username="root", Password="";
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			out.println("<p>Class Load...</p>");
		} 
		catch (Exception e)
		{
			
		}
	}

}
