

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/stmt_insert")
public class stmt_insert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			pw.print("Class Loaded...");
			Connection cnn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "");
			pw.print("Connection...");
			
			
		}
		catch(Exception e)
		{
			System.out.println("Error : " + e);
		}
	}

}
