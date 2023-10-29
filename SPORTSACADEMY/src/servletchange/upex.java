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
@WebServlet("/upex")
public class upex extends HttpServlet {
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
			String gn,en;
			
			gn=request.getParameter("gn");
			en=request.getParameter("en");
			//pw.println(un);
			//pw.println(up);
			int enu=Integer.parseInt(request.getParameter("id"));
			int se=Integer.parseInt(request.getParameter("se"));
			int re=Integer.parseInt(request.getParameter("re"));

			String sql="{CALL myexupdate(?,?,?,?,?)}";
			CallableStatement cstmt = conn.prepareCall(sql);
			cstmt.setString(1, gn);
			cstmt.setString(2, en);
			cstmt.setInt(3, se);
			cstmt.setInt(4, re);
			cstmt.setInt(5, enu);
			cstmt.execute();
			
			cstmt.close();
			conn.close();
			
			response.sendRedirect("fetchexercise");
		
		}
		catch(Exception e)
		{
			pw.println(e.toString());
		}
	}

}
