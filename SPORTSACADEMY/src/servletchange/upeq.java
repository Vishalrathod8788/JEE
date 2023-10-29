package servletchange;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/upeq")
public class upeq extends HttpServlet {
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
String gn,en,co;
			
			gn=request.getParameter("gn");
			en=request.getParameter("en");
			co=request.getParameter("co");

			//pw.println(un);
			//pw.println(up);
			int enu=Integer.parseInt(request.getParameter("id"));
			pw.println(enu);
			int pr=Integer.parseInt(request.getParameter("pr"));
			int tot=Integer.parseInt(request.getParameter("tot"));

			
			String sql="{CALL myequpdate(?,?,?,?,?,?)}";
			CallableStatement cstmt = conn.prepareCall(sql);
			cstmt.setString(2, en);
			cstmt.setString(3, gn);
			cstmt.setInt(4, pr);
			cstmt.setString(5, co);
			cstmt.setInt(6, tot);
			cstmt.setInt(1, enu);
			cstmt.execute();
			
			cstmt.close();
			conn.close();
			response.sendRedirect("fetchequip");
		
		}
		catch(Exception e)
		{
			pw.println(e.toString());
		}

	}

}
