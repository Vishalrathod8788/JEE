<%@page import="java.sql.DriverManager"%>
<%@page import="com.mysql.jdbc.Driver"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
Connection con;
Statement stmt;
ResultSet rs;
int id=Integer.parseInt(request.getParameter("txtID"));
String name=request.getParameter("txtName");
String city=request.getParameter("txtCity");
int choice=Integer.parseInt(request.getParameter("t4"));
	try{
		Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/j2ee","root","");
		stmt=con.createStatement();
		switch(choice){
			case 1:{
				String sql="insert into jsp_tab values("+id+",'"+name+"','"+city+"')";
				stmt.executeUpdate(sql);
				out.println("Inserted Successfully");
				break;
			}
			case 2:{
				String sql="update jsp_tab set name='"+name+"',city='"+city+"' WHERE id="+id+"";
				stmt.executeUpdate(sql);
				out.println("Updated Successfully");
				break;
			}
			case 3:{
				String sql="delete from jsp_tab where id="+id;
				stmt.executeUpdate(sql);
				out.println("Deleted Successfully");
				break;
			}
			case 4:{
				String sql="select * from jsp_tab where id="+id;
				rs=stmt.executeQuery(sql);
				while(rs.next()){
				out.println(rs.getInt(1));
				out.println("<br>");
				out.println(rs.getString(2));
				out.println("<br>");
				out.println(rs.getString(3));
				out.println("<br>----------------<br>");
				}
				break;
			}
			case 5:{
				String sql="select * from jsp_tab";
				rs=stmt.executeQuery(sql);
				while(rs.next()){
				out.println(rs.getInt(1));
				out.println("<br>");
				out.println(rs.getString(2));
				out.println("<br>");
				out.println(rs.getString(3));
				out.println("<br>----------------<br>");
				}
				break;
			}
		}
	}
	catch(Exception e){
	out.println(e.toString());
	}
	%>


</body>
</html>