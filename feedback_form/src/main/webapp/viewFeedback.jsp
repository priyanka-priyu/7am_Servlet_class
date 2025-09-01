<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.sql.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All feedback</title>
</head>
<body>
	<h2>Feedback Received</h2>
	<table border="1">
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Email</th>
			<th>Feedback</th>
		</tr>

		<%
		Statement st = null;
		ResultSet rs = null;
		Connection con = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/feedback_db", "root", "root");
		} catch (Exception e) {
		}

		if (con != null) {
			st = con.createStatement();
			rs = st.executeQuery("select * from feedback");
		}
		while (rs.next()) {
		%>
		<tr>
			<td><%=rs.getInt("id")%></td>
			<td><%=rs.getString("name")%></td>
			<td><%=rs.getString("email")%></td>
			<td><%=rs.getString("feedback")%></td>

		</tr>
		<%
		}
		rs.close();
		st.close();
		con.close();
		%>


	</table>
</body>
</html>