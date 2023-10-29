package servletchange;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/submitfee")
public class submitfee extends HttpServlet {
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
			
			
			//int studno=Integer.parseInt(request.getParameter("sid"));
			String sname=request.getParameter("snam");
			//pw.println(sname);
			
			/*
			String sql="{CALL selfeeno(?)}";
			CallableStatement cstmt = conn.prepareCall(sql);
			cstmt.setString(1, sname);
			//cstmt.registerOutParameter(2,Types.INTEGER);
			cstmt.execute();
			//String n,no;
			//n=cstmt.getString(2);
			//pw.println("name of student number"+stno+"is "+n);
			String i;
			i=cstmt.getString(1);
			pw.println(i);*/
			
			//String name=cstmt.getString(2);
			//String n=cstmt.getString(1);
			
			qry="SELECT * FROM `stud` WHERE `name`='"+sname+"'";
			stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery(qry);
			if(rs.next())
			{
				int i=rs.getInt(1);
				//pw.println(i);
				pw.println("<html>");
				pw.println("<head>");
				pw.println("<link href='f1.css' rel='stylesheet'>");
				pw.println("</head>");
				pw.println("<body>");
				pw.println("<h2>YOUR STUD ID NO:"+i+"(REMEMBER IT)</h1>");
				pw.println("<div class='container'>");	
				pw.println("<form action='payfee?sid="+i+"' method='post'>"); 
				//pw.println("<input type='hidden' name='id' value='"+rs.getInt(1)+"' ");
				pw.println("<h2>PLEASE SUBMIT YOUR FEE HERE:</h1>");
							
				pw.println(" <label>ENTER STUDENT ID NO:</label>	");	
				pw.println("<input type='text' name='sid'>");
				
				pw.println(" <label>PAY HERE :</label>	");	
				pw.println("<select>");
				pw.println("<option>1000(INR)</option>");
				pw.println("</select>");
				
				
				
				
				
									
							
					      
					
									 
					
						
				pw.println("<input type='submit' value='PAY'>");     				
				pw.println(" </form>");   
				pw.println("</div>");  


				pw.println("</body>");
				pw.println("</html>");
			

			}
			else
			{
				response.sendRedirect("validid.html");
			}
			
			
			
			
			
			
			
			
			
			
						
		}
		catch(Exception e)
		{
			pw.println(e.toString());
		}

	}

}
