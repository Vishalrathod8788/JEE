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
@WebServlet("/getcourt")
public class getcourt extends HttpServlet {
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
			qry="select * from court where no="+id+"";
			ResultSet rs=conn.createStatement().executeQuery(qry);
			rs.next();
			pw.println("<html>");
			pw.println("<head>");
			pw.println("<meta charset='ISO-8859-1'>");
			pw.println("<title>Insert title here</title>");
			pw.println("<link href='f1.css' rel='stylesheet'>");
			pw.println("</head>");
			pw.println("<body>");		
			pw.println("<div class='container'>");			
			pw.println("<form action='upcourt' method='post'>");
			//pw.println("<input type='hidden' name='id' value='"+rs.getInt(1)+"' ");
			pw.println("<label>Game Name:</label>");					  	
			pw.println("<input type='text' name='gn'value='"+rs.getString(2)+"' readonly");								
			pw.println("<label>Total Court/Ground :</label>");				      
			pw.println("<input type='text' name='gr' value='"+rs.getInt(3)+"' required>");				      
			pw.println("<input type='submit' value='UPDATE GROUND/COURT'>");     				
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
