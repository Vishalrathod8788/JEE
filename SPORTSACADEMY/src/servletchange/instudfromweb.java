package servletchange;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/instudfromweb")
public class instudfromweb extends HttpServlet {
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
			String snam,gen,ad,cnumb,gn,bt,sname;
			sname=request.getParameter("sn");
			snam=sname.toUpperCase();
			gen=request.getParameter("gender");
			ad=request.getParameter("addr");
			gn=request.getParameter("gnam");
			bt=request.getParameter("bt");
			cnumb=request.getParameter("cont");
			
			String date=request.getParameter("date");
			stmt=conn.createStatement();
			qry="select * from stud where name='"+snam+"'";
			ResultSet rs=stmt.executeQuery(qry);
			//rs.next();
			
			
			if(rs.next())
			{
				pw.println("student already exist");
				response.sendRedirect("invstud1.html");
				
			}
			else
			{
				//response.sendRedirect("fetchtab");
				 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		            LocalDate localDate = LocalDate.parse(date, formatter);
				//int ag=Integer.parseInt(request.getParameter("age"));
		            String forage=request.getParameter("date");
					DateTimeFormatter formatterr = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					LocalDate localDateL = LocalDate.parse(forage, formatterr);
					LocalDate birthdate = LocalDate.parse(forage);
					pw.println(localDateL);

					// Get the current date
					LocalDate currentDate = LocalDate.now();
					int ag = Period.between(birthdate, currentDate).getYears();
				
				
				String sql="{CALL mystudins(?,?,?,?,?,?,?,?)}";
				CallableStatement cstmt = conn.prepareCall(sql);
				cstmt.setString(1, snam);
	            cstmt.setObject(2, java.sql.Date.valueOf(localDate)); // Convert LocalDate to java.sql.Date
	            cstmt.setInt(3, ag);
				cstmt.setString(4, gen);
				cstmt.setString(5, ad);
				cstmt.setString(6, gn);
				cstmt.setString(7, cnumb);
				cstmt.setString(8, bt);
				

				cstmt.execute();
				
				cstmt.close();
				conn.close();
				response.sendRedirect("submitfee?snam="+snam+"");
			}
			
			/*SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			Date db=(Date) outputDateFormat.parse(date);*/
			
		}
		catch(Exception e)
		{
			pw.println(e.toString());
		}

	}

}
