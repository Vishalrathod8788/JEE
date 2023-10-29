package servletchange;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/payfee")
public class payfee extends HttpServlet {
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
		PrintWriter pw = response.getWriter();
		
		
		Connection conn;
		String qry,url,user,pass;
		//java.sql.PreparedStatement pstmt;
		//PreparedStatement pstmt;
		Statement  stmt;
		
		
		url="jdbc:mysql://localhost:3306/hjd?characterEncoding=utf8";
		user="root";
		pass="";
		
		try 
		{
			
			Class.forName("com.mysql.jdbc.Driver");
			//pw.println("class loaded");
			
			conn=DriverManager.getConnection(url, user, pass);
			//pw.println("conn done");
			int s=Integer.parseInt(request.getParameter("sid"));
			//pw.println(s);
			
			qry="insert into studfee(sno,issubmit) values('"+s+"',1)";
			stmt=conn.createStatement();
			stmt.execute(qry);
			//pw.println("done");
			response.sendRedirect("feedone.html");
			
			
		}
		catch(Exception e)
		{
			pw.println(e.toString());
		}

	}

}
