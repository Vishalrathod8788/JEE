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


@WebServlet("/updiet")
public class updiet extends HttpServlet {
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
			//pw.println("conn done");
				String gn,ageg,gen,meal;
			
			gn=request.getParameter("gn");
			ageg=request.getParameter("ageg");
			gen=request.getParameter("gen");
			meal=request.getParameter("meal");
			//pw.println(un);
			//pw.println(up);
			int dnu=Integer.parseInt(request.getParameter("id"));
			

			String sql="{CALL mydietupdate(?,?,?,?,?)}";
			CallableStatement cstmt = conn.prepareCall(sql);
			cstmt.setString(1, gn);
			cstmt.setString(2, ageg);
			cstmt.setString(3, gen);
			cstmt.setString(4, meal);
			cstmt.setInt(5, dnu);
			cstmt.execute();
			
			cstmt.close();
			conn.close();
			
			response.sendRedirect("fetchdiet");
		
		}
		catch(Exception e)
		{
			pw.println(e.toString());
		}
		
	}

}
