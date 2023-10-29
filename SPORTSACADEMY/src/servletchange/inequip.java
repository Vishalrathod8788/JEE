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
@WebServlet("/inequip")
public class inequip extends HttpServlet {
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
			String en,gn,co,enam;
			int pr,tot;
			enam=request.getParameter("eq");
			en=enam.toUpperCase();
			gn=request.getParameter("gnam");
			co=request.getParameter("cmp");
			
			pr=Integer.parseInt(request.getParameter("pr"));
			tot=Integer.parseInt(request.getParameter("tot"));
			
			
			stmt=conn.createStatement();
			qry="select * from equipments where eqname='"+en+"' AND price='"+pr+"' AND company='"+co+"'";
			ResultSet rs=stmt.executeQuery(qry);
			//rs.next();
			
			
			if(rs.next())
			{
				pw.println("username already exist");
				response.sendRedirect("invequip.html");
				
			}
			else
			{
				//response.sendRedirect("fetchequip");
				String sql="{CALL myeqinsert(?,?,?,?,?)}";
				CallableStatement cstmt = conn.prepareCall(sql);
				cstmt.setString(1, gn);
				cstmt.setString(2, en);
				cstmt.setInt(3, pr);
				cstmt.setString(4, co);
				cstmt.setInt(5, tot);
				cstmt.execute();
				
				cstmt.close();
				conn.close();
				response.sendRedirect("fetchequip");
			}

			
		}
		catch(Exception e)
		{
			pw.println(e.toString());
		}

	}

}
