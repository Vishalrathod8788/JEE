

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

@WebServlet("/stmt_insert")
public class stmt_insert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		int id = Integer.parseInt(request.getParameter("txtID"));
		String name = request.getParameter("txtName");
		String gender = request.getParameter("txtGender");
		String city = request.getParameter("choice");
		int choice = Integer.parseInt(request.getParameter("choice"));
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			pw.print("Class Loaded...");
			Connection cnn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "");
			pw.print("Connection...");
			
			switch (choice) 
			{
				case 1:
				{
					String Qry = "insert into tbl_stud (id, name, gender, city) values(?, ?, ?, ?)";
					PreparedStatement pstmt = (PreparedStatement) cnn.prepareStatement(Qry);
					pstmt.setInt(1, id);
					pstmt.setString(2, name);
					pstmt.setString(3, gender);
					pstmt.setString(4, city);
					
					pstmt.execute();
					
					pw.print("Record inserted...");
					
				}	
				
				break;
				
				case 2:
				{
					String Qry = "update tbl_stud set name= ?, gender = ?, city=? where id=? ";
					PreparedStatement pstmt = (PreparedStatement) cnn.prepareStatement(Qry);
					pstmt.setInt(4, id);
					pstmt.setString(1, name);
					pstmt.setString(2, gender);
					pstmt.setString(3, city);
					
					pstmt.execute();
					
					pw.print("Record Updated...");
					
				}	
				break;
				
				case 3:
				{
					String Qry = "delete from tbl_stud where id=?";
					PreparedStatement pstmt = (PreparedStatement) cnn.prepareStatement(Qry);
					pstmt.setInt(1, id);
					
					pstmt.execute();
					
					pw.print("Record Delete...");
				}	
				break;
				
				default:
					pw.print("Please Select in 1 to 3");
					break;
			}			
		}
		catch(Exception e)
		{
			System.out.println("Error : " + e);
		}
	}
}
