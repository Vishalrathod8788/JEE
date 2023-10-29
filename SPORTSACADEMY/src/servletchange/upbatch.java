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
@WebServlet("/upbatch")
public class upbatch extends HttpServlet {
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
			pw.println("conn done");
				String bn,bt;
			
			bn=request.getParameter("bn");
			//ageg=request.getParameter("ageg");
			//gen=request.getParameter("gen");
			bt=request.getParameter("bt");
			pw.println(bn);
			pw.println(bt);
			//pw.println(ageg);
			//pw.println(gen);
			int bnu=Integer.parseInt(request.getParameter("id"));
			pw.println(bnu);
			

			String sql="{CALL mybatchupdate(?,?,?)}";
			CallableStatement cstmt = conn.prepareCall(sql);
			cstmt.setString(2, bn);
			cstmt.setString(3, bt);
			//cstmt.setString(4, ageg);
			//cstmt.setString(5, gen);
			cstmt.setInt(1, bnu);
			cstmt.execute();
			
			cstmt.close();
			conn.close();
			
			response.sendRedirect("fetchbatch");
		
		}
		catch(Exception e)
		{
			pw.println(e.toString());
		}

	}

}
