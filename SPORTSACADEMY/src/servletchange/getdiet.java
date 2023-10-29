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
@WebServlet("/getdiet")
public class getdiet extends HttpServlet {
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
			qry="select * from diet where dno="+id+"";
			ResultSet rs=conn.createStatement().executeQuery(qry);
			rs.next();
			//String agegroup,gender;
			
			
			
			pw.println("<html>");
			pw.println("<head>");
			pw.println("<link href='f1.css' rel='stylesheet'>");
			pw.println("</head>");
			pw.println("<body>");

			pw.println("<div class='container'>");		

			pw.println("<form action='updiet' method='post'>"); 
			pw.println("<input type='hidden' name='id' value='"+rs.getInt(1)+"' ");
						
			pw.println("<label>Game Name:</label>");					  	
			pw.println("<input type='text' name='gn' value='"+rs.getString(2)+"' readonly");
			
			
			if(rs.getString(3).equals("ADULT"))
			{
				pw.println("<label>Age : <input type='radio' name='ageg' value='ADULT' checked required>ADULT");				      
				pw.println("<input type='radio' name='ageg' value='CHILD' required>CHILD");				      		
						pw.println("</label>");		
			}
			else
			{
				pw.println("<label>Age : <input type='radio' name='ageg' value='ADULT'  required>ADULT");				      
				pw.println("<input type='radio' name='ageg' value='CHILD' checked required>CHILD");				      		
						pw.println("</label>");		
			}
							
			if(rs.getString(4).equals("MALE"))
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
				
		      
							      
							      
												pw.println("<label>Meal textarea :</label>");
							     	pw.println("<textarea name='meal'  rows='4' defaultValue='"+rs.getString(5)+"' required>"+rs.getString(5)+" </textarea>");	       
							
							
											     
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
