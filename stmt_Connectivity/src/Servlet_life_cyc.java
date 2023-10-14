

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Servlet_life_cyc")
public class Servlet_life_cyc extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
	    // Initialization code here
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    // Request handling code here
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    // Handling HTTP GET requests
	}
	
	public void destroy() {
	    // Cleanup code here
	}

}
