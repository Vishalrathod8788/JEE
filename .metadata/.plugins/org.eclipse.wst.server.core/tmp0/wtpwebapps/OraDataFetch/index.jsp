<%@page import="java.util.Date"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
	td {
		border-bottom:2px solid navy;
		padding:4px;
		border-bottom-left-radius:10px;
		border-bottom-right-radius:10px;
		font-family:verdana;
		box-shadow:2px 2px 4px gray;
	}
	th{
		border-bottom:2px solid navy;
		padding:10px;
		border-bottom-left-radius:10px;
		border-bottom-right-radius:10px;
		font-family:verdana;
		font-size:20px;
		box-shadow:3px 4px 8px gray;
	}
	
	caption{
		border-bottom:2px solid navy;
		border-bottom-left-radius:10px;
		border-bottom-right-radius:10px;
		font-family:chiller;
		font-size:40px;
		box-shadow:2px 2px 9px gray;
	}
</style>
</head>
<body>
	<table style="padding:15px;text-align:center;">		
		<caption>EMP TABLE</caption>
		<tr style="border:1px solid navy; padding:20px;">
			<th >Employee No</th>
			<th >Employee Name</th>
			<th >Job</th>
			<th >Manager</th>
			<th >Hiredate</th>
			<th >Salary</th>
			<th >Commision</th>
			<th >Department No</th>
		</tr>

		<%
		String url = "jdbc:oracle:thin:@localhost:1521:XE", userName = "protonium", passWord = "tiger";
		Connection conn;
		Statement stmt;
		PreparedStatement ps;
		ResultSet rs;

		try {
			/* out.println("URL SUCCEDED"); */
			Class.forName("oracle.jdbc.driver.OracleDriver");
			/* out.println("DRIVER LOADED"); */
			conn = DriverManager.getConnection(url, userName, passWord);
			ps = conn.prepareStatement("SELECT EMPNO ,ENAME , JOB ,	MGR , HIREDATE , SAL , COMM , DEPTNO  FROM EMP");
			rs = ps.executeQuery();
			while (rs.next()) {
				int no = rs.getInt("EMPNO");
				String name = rs.getString("ENAME");
				String job = rs.getString("JOB");
				int mgr = rs.getInt("MGR");
				Date date = rs.getDate("HIREDATE");
				int sal = rs.getInt("SAL");
				int comm = rs.getInt("COMM");
				int deptno = rs.getInt("DEPTNO");
		%>
		<tr style="border-bottom: 5px SOLID BLACK; padding:0px;">
			<td ><%= no%></td>
			<td ><%= name%></td>
			<td ><%= job%></td>
			<td ><%= mgr%></td>
			<td ><%= date%></td>
			<td ><%= sal%></td>
			<td ><%= comm%></td>
			<td ><%= deptno%></td>
		</tr>
	<%
	}
	} catch (Exception e) {

	}
	%>
</table>
</body>
</html>