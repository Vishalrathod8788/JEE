package connect;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/connectivity")
public class connectivity extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
		Connection cnn;
		Statement stmt = null;
		String URL, Username = "root", Password =  "", qry;
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		URL = "jdbc:mysql://localhost:3306/vgr";
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			out.println("<p>Class Load...</p>");
			cnn = DriverManager.getConnection(URL, Username, Password);
			out.println("<p>Connection Success...</p>");
			
			stmt = cnn.createStatement();
			
			String u = request.getParameter("txtName");
			String p = request.getParameter("txtPass");
			
			qry = "INSERT INTO `tbl_user`(`name`, `password`) VALUES ('"+u+"','"+p+"')";
			
			stmt.execute(qry);
			out.println(u+"<br>");
			
			out.println(p+"<br>");
		} 
		catch (Exception e) 
		{
		
		}
//		doGet(request, response);
//		Connection conn;
//		Statement stmt = null;
//		String URL, qry, Username, Password;
//		Username = "root";
//		Password = "";
//		response.setContentType("text/html");
//		PrintWriter out = response.getWriter();
//		URL = "jdbc:mysql://localhost:3306/vgr";
//		try {
//			
//			Class.forName("com.mysql.jdbc.Driver");
//			out.println("<p>Class Load");
//			conn = DriverManager.getConnection(URL, Username, Password);
//			out.println("<p>Connection...");
//			stmt = conn.createStatement();
//				
//			String u = request.getParameter("txtName");
//			String p = request.getParameter("txtPass");
//			qry = "INSERT INTO `tbl_user`(`name`, `password`) VALUES ('"+u+"','"+p+"')";
//			
//			stmt.execute(qry);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		                                                                                                                              
//		}
		
		
	}

}
