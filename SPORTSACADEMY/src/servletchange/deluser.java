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

@WebServlet("/deluser")
public class deluser extends HttpServlet {
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
		
		String url,user,pass;
		url="jdbc:mysql://localhost:3306/hjd?characterEncoding=utf8";
		user="root";
		pass="";
		
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			//pw.println("class loaded");
			
			conn=DriverManager.getConnection(url, user, pass);
			pw.println("conn done");
			
		
		int unum = Integer.parseInt(request.getParameter("id")); 
		pw.println(unum);
		String sql="{CALL myuserdelete(?)}";
		CallableStatement cstmt = conn.prepareCall(sql);
		cstmt.setInt(1,unum);
		cstmt.execute();
		
		cstmt.close();
		conn.close();
		
		response.sendRedirect("fetchtab");
		//qry="select * from st where idno="+ id+"";
		//ResultSet rs=conn.createStatement().executeQuery(qry);
		}
		catch(Exception e)
		{
			pw.println(e.toString());
		}

	}

}