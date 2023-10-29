package servletchange;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/inbatch")
public class inbatch extends HttpServlet {
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
			pw.println("class loaded");
			
			conn=DriverManager.getConnection(url, user, pass);
			pw.println("connection done");
			String bn,bt;
			bn=request.getParameter("gnam");
			bt=request.getParameter("btime");
			//ageg=request.getParameter("ageg");
			//gen=request.getParameter("gender");
			pw.println(bn);
			pw.println(bt);
			
			stmt=conn.createStatement();
			qry="select * from batches where batchname='"+bn+"' AND TIME='"+bt+"'";
			ResultSet rs=stmt.executeQuery(qry);
			//rs.next();
			
			
			if(rs.next())
			{
				//pw.println("username already exist");
				response.sendRedirect("invbatch.html");
				
			}
			else
			{
				//response.sendRedirect("fetchtab");
				String sql="{CALL mybatchinsert(?,?)}";
				CallableStatement cstmt = conn.prepareCall(sql);
				cstmt.setString(1, bn);
				cstmt.setString(2, bt);
				//cstmt.setString(3, ageg);
				//cstmt.setString(4, gen);
				cstmt.execute();
				
				cstmt.close();
				conn.close();
				response.sendRedirect("fetchbatch");
			}
			
			


		}
		catch(Exception e)
		{
			pw.println(e.toString());
		}

	}

}
