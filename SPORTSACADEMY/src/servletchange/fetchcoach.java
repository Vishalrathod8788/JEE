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

@WebServlet("/fetchcoach")
public class fetchcoach extends HttpServlet {
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
			
			
			qry="select * from coach";
			stmt=conn.createStatement();
			ResultSet rs = stmt.executeQuery(qry);
			
			pw.println("<html>");
			pw.println("<head>");
			pw.println("<link rel='stylesheet' type='text/css' href='fetch.css'>\r\n"
					+ "");
			pw.println();
			pw.println();
			pw.println();
			pw.println();

			pw.println("<br><br><br>");
			pw.println("<table border='5' align='center'>");
			pw.println("<tr><th>COACH-NAME</th><th>DOB</th><th>AGE</th><th>GENDER</th><th>ADDRESS</th><th>GAME</th><th>QUALIFICATION</th><th>EXPERIENCE</th><th>ACHIEVEMNETS</th><th>CONTACT NO</th><th>ACTION</th><tr>");
			while(rs.next())
			{
				pw.println("<tr>");

				pw.println("<td>" + rs.getString(2) + "</td>");
				pw.println("<td>" + rs.getDate(3) + "</td>");
				pw.println("<td>" + rs.getInt(4) + "</td>");
				pw.println("<td>" + rs.getString(5) + "</td>");
				pw.println("<td>" + rs.getString(6) + "</td>");
				pw.println("<td>" + rs.getString(7) + "</td>");
				pw.println("<td>" + rs.getString(8) + "</td>");
				pw.println("<td>" + rs.getString(9) + "</td>");
				pw.println("<td>" + rs.getString(10) + "</td>");
				pw.println("<td>" + rs.getString(11) + "</td>");
				pw.println("<td><a href='/SPORTSACADEMY/getcoach?id="+ rs.getString(1) +"'>update</a>||<a href='/SPORTSACADEMY/delcoach?id="+rs.getInt(1)+"'>delete</a></td> ");	

				pw.println("</tr>");	
				
			}
			
			
			 	pw.println("<div class='links-container'>");
			    pw.println("<p><a href='welcuser.html'>BACK</a></p>");
			    pw.println("<p><a href='frmcoach.html'>ADD RECORDS</a></p>");
			    pw.println("</div>");
		}
		
		catch(Exception e)
		{
			pw.println(e.toString());
		}
	}

}
