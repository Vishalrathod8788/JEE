
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
import com.oracle.webservices.internal.api.EnvelopeStyle.Style;

@WebServlet("/Update_demo")
public class Update_demo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("tetx/html");
		PrintWriter out = response.getWriter();
		String name = request.getParameter("txtName");
		String password = request.getParameter("txtPass");
		try 
		{
			
			Class.forName("com.mysql.jdbc.Driver");
			out.print("Class Load...");
			
			Connection cnn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "");
			
			out.print("Connection Success...");
			
			String Qry = "update tbl_login set password = ? where name = ?";
			
			PreparedStatement pstmt = (PreparedStatement) cnn.prepareStatement(Qry);
			
			pstmt.setString(2, name);
			pstmt.setString(1, password);
			
			pstmt.executeUpdate();
			
			out.print("Record Updated...");
			
		} 
		catch (Exception e) 
		{
			System.out.println("Error : " + e);
		}
	}

}
