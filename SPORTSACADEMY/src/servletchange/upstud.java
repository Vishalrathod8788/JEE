package servletchange;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/upstud")
public class upstud extends HttpServlet {
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
		//Statement stmt;
		String url,user,pass;
		url="jdbc:mysql://localhost:3306/hjd?characterEncoding=utf8";
		user="root";
		pass="";
		
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			pw.println("class loaded");
			
			conn=DriverManager.getConnection(url, user, pass);
			String sn,ad,gn,cnumb,bt,gen,date,sname;
			int snu,ag;
			
			
			snu=Integer.parseInt(request.getParameter("id"));
			ag=Integer.parseInt(request.getParameter("ag"));
			pw.println(snu);

			
			sname=request.getParameter("sn");
			sn=sname.toUpperCase();
			ad=request.getParameter("ad");
			gn=request.getParameter("gn");
			pw.println(gn);
			cnumb=request.getParameter("cnum");
			bt=request.getParameter("bt");
			gen=request.getParameter("gen");
			date=request.getParameter("dt");
			pw.println(date);
			 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	            LocalDate localDate = LocalDate.parse(date, formatter);
			
			
			String sql="{CALL mystuup(?,?,?,?,?,?,?,?,?)}";
			CallableStatement cstmt = conn.prepareCall(sql);
			cstmt.setString(2,sn);
			cstmt.setObject(3,java.sql.Date.valueOf(localDate));
			//cstmt.setString(3,date);
			cstmt.setInt(4,ag);
			cstmt.setString(5,gen);
			cstmt.setString(6,ad);
			pw.println("bef");
			cstmt.setString(7,gn);
			pw.println("af");
			cstmt.setString(8,cnumb);
			pw.println("af2");
			cstmt.setString(9,bt);
			pw.println("af3");
			cstmt.setInt(1,snu);
			pw.println("af4");
			cstmt.execute();
			pw.println("af5");
			cstmt.close();
			conn.close();
			
			response.sendRedirect("fetchstud");
			
		}
		catch(Exception e)
		{
			pw.println(e.toString());
		}

	}

}
