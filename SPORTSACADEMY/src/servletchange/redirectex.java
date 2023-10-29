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

@WebServlet("/redirectex")
public class redirectex extends HttpServlet {
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
			String qry,url,user,pass;
			//java.sql.PreparedStatement pstmt;
			//PreparedStatement pstmt;
			//Statement  stmt;
			
			
			url="jdbc:mysql://localhost:3306/hjd?characterEncoding=utf8";
			user="root";
			pass="";
			
			try {
				
				Class.forName("com.mysql.jdbc.Driver");
				//pw.println("class loaded");
				
				conn=DriverManager.getConnection(url, user, pass);
			
			int i=Integer.parseInt(request.getParameter("sid"));
			String gn=request.getParameter("gn");
			pw.println(gn);
			
			
			
			qry="select * from studfee where sno='"+i+"' and issubmit=1";
			ResultSet re=conn.createStatement().executeQuery(qry);
			
			
			if(re.next())
			{
			
				if(gn.equals("CRICKET"))
				{
					response.sendRedirect("fetchcrex?gn="+gn+"");
				}
				else if(gn.equals("KABBADI"))
				{
					response.sendRedirect("fetchkabex?gn="+gn+"");
				}
				else if(gn.equals("TENNIS"))
				{
					response.sendRedirect("fetchtenex?gn="+gn+"");
				}
				else if(gn.equals("BADMINTON"))
				{
					response.sendRedirect("fetchbadex?gn="+gn+"");
				}
				else if(gn.equals("VOLLEYBALL"))
				{
					response.sendRedirect("fetchvolex?gn="+gn+"");
				}
			
			}
			
			
			else
			{
				response.sendRedirect("feenotsubmit.html");
			}
			
			
			}
			catch(Exception e)
			{
				pw.println(e.toString());
			}

	}

}
