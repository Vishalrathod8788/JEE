package pack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.corba.se.impl.ior.GenericTaggedComponent;

@WebServlet("/Login_Servlet")
public class Login_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		
		PrintWriter pw = response.getWriter();
		Connection cnn;
		Statement stmt;
		String URL, Username, Password;
		URL = "jdbc:mysql://localhost:3306/Demo";
		Username = "root";
		Password = "";
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			pw.println("Class Load...");
			cnn = DriverManager.getConnection(URL, Username, Password);
			pw.println("Connection Success...");
			
			stmt = cnn.createStatement();
			
			String name = request.getParameter("txtName");
			String pass = request.getParameter("txtPass");
			
			
			String qry = "SELECT * FROM `tbl_demo` ";
			
			stmt.execute(qry);
			
			if(name == name && pass == pass)
			{
				getServletContext().setAttribute("name", name);
				
				request.getRequestDispatcher("welcome_servlet").forward(request, response);
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}		
		
	}

}
