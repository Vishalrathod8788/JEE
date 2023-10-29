package servletchange;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/fetchdiet")
public class fetchdiet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		
		Connection conn;
		Statement stmt;
		String url,user,pass,qry;
		url="jdbc:mysql://localhost:3306/hjd?characterEncoding=utf8";
		user="root";
		pass="";
		
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			//pw.println("class loaded");
			
			conn=DriverManager.getConnection(url, user, pass);
			//pw.println("conn done");
			
			
			qry="select * from diet";
			stmt=conn.createStatement();
			ResultSet rs = stmt.executeQuery(qry);
			
			pw.println("<html>");
			pw.println("<head>");
			pw.println("<link rel='stylesheet' type='text/css' href='fetch.css'>\r\n"
					+ "");
			pw.println("<br><br><br>");
			pw.println("<table border='5' align='center'>");
			pw.println("<tr><th>GAME</th><th>PLAN NAME</th><th>DETAILS</th><tr>");
			
			while(rs.next())
			{
				pw.println("<tr>");
				pw.println("<td>" + rs.getString(2) + "</td>");
				pw.println("<td>" + rs.getString(3) + "</td>");
				if(rs.getString(2).equals("CRICKET"))
				{
					pw.println("<td><a href='cricdiet.html'>VIEW DIET PLAN</a></td>");
				}
				if(rs.getString(2).equals("BADMINTON"))
				{
					pw.println("<td><a href='baddiet.html'>VIEW DIET PLAN</a></td>");
				}
				if(rs.getString(2).equals("TENNIS"))
				{
					pw.println("<td><a href='tendiet.html'>VIEW DIET PLAN</a></td>");
				}
				if(rs.getString(2).equals("VOLLEYBALL"))
				{
					pw.println("<td><a href='voldiet.html'>VIEW DIET PLAN</a></td>");
				}
				if(rs.getString(2).equals("KABADDI"))
				{
					pw.println("<td><a href='kabdiet.html'>VIEW DIET PLAN</a></td>");
				}
				//pw.println("<td>" + rs.getString(4) + "</td>");
				//pw.println("<td>" + rs.getString(5) + "</td>");
				//pw.println("<td><a href='/project/getdiet?id="+ rs.getString(1) +"'>update</a>||<a href='/project/deldiet?id="+rs.getInt(1)+"'>delete</a></td> ");	
				
				pw.println("</tr>");	
				
			}
			
			
			
			 pw.println("<div class='links-container'>");
			pw.println("<p><a href='welcuser.html'>BACK</a></p>");
			//pw.println("<p><a href='frmdiet.html'>ADD PLAN</a></p>");
			pw.println("</div>");
		}
		
		catch(Exception e)
		{
			pw.println(e.toString());
		}
	}

}
