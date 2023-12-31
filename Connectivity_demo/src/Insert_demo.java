

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.PreparedStatement;

@WebServlet("/Insert_demo")
public class Insert_demo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String Qry;
		
		String name = request.getParameter("txtName");
		String password = request.getParameter("txtPass");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			pw.print("Class Loaded...");
			Connection cnn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "");
			pw.print("Connection Done...");
			
			Qry = "insert into tbl_login (name, password) values(?, ?) ";
			
			PreparedStatement pstmt =  (PreparedStatement) cnn.prepareStatement(Qry);
			pstmt.setString(1, name);
			pstmt.setString(2,  password);
			
			pstmt.execute();
			pw.print("Recored inserted...");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
