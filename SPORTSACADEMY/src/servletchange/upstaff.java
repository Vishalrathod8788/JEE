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
@WebServlet("/upstaff")
public class upstaff extends HttpServlet {
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
			//pw.println("class loaded");
			
			conn=DriverManager.getConnection(url, user, pass);
			//pw.println("conn done");
			String sn,ad,cnum,gen,date,dep,po,ql,ex;
			int snu,ag;
			
			
			snu=Integer.parseInt(request.getParameter("id"));
			ag=Integer.parseInt(request.getParameter("ag"));
			pw.println(snu);

			
			sn=request.getParameter("sn");
			ad=request.getParameter("ad");
			dep=request.getParameter("dep");
			cnum=request.getParameter("cnum");
			gen=request.getParameter("gen");
			date=request.getParameter("db");
			ql=request.getParameter("ql");
			po=request.getParameter("po");
			ex=request.getParameter("ex");
			dep=request.getParameter("dep");
			
			pw.println(sn);
			pw.println(ad);
			pw.println(dep);
			pw.println(cnum);
			pw.println(gen);
			//pw.println(date);
			pw.println(ql);
			pw.println(po);
			pw.println(ex);
			pw.println(ag);
			
			
			
			pw.println("bef date");
			 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	            LocalDate localDate = LocalDate.parse(date, formatter);
	            pw.println("bef date");
			
	            pw.println(date);
			
			String sql="{CALL mystaup(?,?,?,?,?,?,?,?,?,?,?)}";
			CallableStatement cstmt = conn.prepareCall(sql);
			cstmt.setString(2, sn);
			cstmt.setObject(3, java.sql.Date.valueOf(localDate));
			cstmt.setInt(4, ag);
			cstmt.setString(5, gen);
			cstmt.setString(6, ad);
			cstmt.setString(7, dep);
			cstmt.setString(8, po);
			cstmt.setString(9, ql);
			cstmt.setString(10, ex);
			cstmt.setString(11, cnum);
			cstmt.setInt(1, snu);
			cstmt.execute();
			
			pw.println("done");
			cstmt.close();
			conn.close();
			
			response.sendRedirect("fetchstaff");
			
		}
		catch(Exception e)
		{
			pw.println(e.toString());
		}

	}

}
