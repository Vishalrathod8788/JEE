

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.PreparedStatement;

@WebServlet("/Delete_demo")
public class Delete_demo extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String name = request.getParameter("txtName");
		
		try 
		{
			
			Class.forName("com.mysql.jdbc.Driver");
			out.print("Clas Loaded...");
			
			Connection cnn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "");
			out.print("Connection Success...");
			
			String Qry = "Delete from tbl_login where name = ?";
			
			PreparedStatement pstmt = (PreparedStatement) cnn.prepareStatement(Qry);
			pstmt.setString(1, name);
			
			pstmt.execute();
			out.print("Recorde Deleted...");
		}
		catch (Exception e)
		{
			System.out.println("Error : " + e);
		}
	}

}
