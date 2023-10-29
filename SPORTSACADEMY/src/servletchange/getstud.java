package servletchange;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/getstud")
public class getstud extends HttpServlet {
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
		String url,user,pass,qry;
		url="jdbc:mysql://localhost:3306/hjd?characterEncoding=utf8";
		user="root";
		pass="";
		
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			//pw.println("class loaded");
			
			conn=DriverManager.getConnection(url, user, pass);
			//pw.println("conn done");
			String id = request.getParameter("id");
			qry="select * from stud where no="+id+"";
			ResultSet rs=conn.createStatement().executeQuery(qry);
			rs.next();
			pw.println("<html>");
			pw.println("<head>");
			pw.println("<link href='f1.css' rel='stylesheet'>");
			pw.println("</head>");
			pw.println("<body>");
			pw.println("<div class='container'>");
			pw.println("<form action='upstud' method='post'>");    	
			pw.println("<input type='hidden' name='id' value='"+rs.getInt(1)+"' ");
			pw.println(" <label>Full Name:</label>");				     
			pw.println("<input type='text' name='sn' value='"+rs.getString(2)+"' required>");				      
							
			pw.println("<label>Date of Birth:</label>");				      
			pw.println("<input type='date' name='dt' value='"+rs.getDate(3)+"' required>");				      
							
			pw.println("<label>Age:</label>");				      
			pw.println("<input type='text' name='ag' value='"+rs.getInt(4)+"' required>");				      
		
			if(rs.getString(5).equals("MALE"))
			{
				pw.println("<label>Gender:");
				pw.println(" <input type='radio'  name='gen' value='MALE' checked required>MALE");		  
						pw.println("<input type='radio'  name='gen' value='FEMALE' required>FEMALE");
								pw.println("</label>");	  
				
			}
			else
			{
				pw.println("<label>Gender:");
				pw.println(" <input type='radio'  name='gen' value='MALE'  required>MALE");		  
						pw.println("<input type='radio'  name='gen' value='FEMALE' checked required>FEMALE");
								pw.println("</label>");	  
			}
			      				 			
								pw.println("<label>Game :</label>");	  	
								pw.println("<input type='text' name='gn' value='"+rs.getString(7)+"' readonly");
													
								pw.println("<label>Address:</label>");					      
								pw.println("<textarea name='ad'  rows='4'   required>"+rs.getString(6)+"</textarea>"); 
							
								pw.println("<label>Contact No:</label>");  
								pw.println("<input type='text'  name='cnum' value='"+rs.getString(8)+"' required>");  

								pw.println("<label>Batch Time :</label>");	   	
								pw.println("<select name='bt' value='"+rs.getString(9)+"' text='"+rs.getString(9)+"'>");
								pw.println("<option>"+rs.getString(9)+"</option>");							

									pw.println("<option>6 : 00 AM  TO  7 : 00 AM</option>");		
									pw.println("<option>7 : 00 AM  TO  8 : 00 AM</option>");		
									pw.println("<option>8 : 00 AM  TO  9 : 00 AM</option>");		
									pw.println("<option>5 : 00 PM  TO  6 : 00 PM</option>");		
									pw.println("<option>6 : 00 PM  TO  7 : 00 PM</option>");		
									pw.println("<option>7 : 00 PM  TO  8 : 00 PM</option>");	
									pw.println("</select>");	
								

									pw.println("<input type='submit' value='UPDATE'>");	
									pw.println("</form>");    
									pw.println("</div>");  

									pw.println("</body>");	
									pw.println("</html>");		
		
		}
		catch(Exception e)
		{
			pw.println(e.toString());
		}

	}

}
