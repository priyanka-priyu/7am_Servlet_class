package com.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class feedbackServlet extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String feedback = req.getParameter("feedback");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/feedback_db", "root", "root");
			System.out.println(con + "con");
			if (con != null) {
				System.out.println("connected...");
			}

			PreparedStatement ps = con.prepareStatement("Insert into feedback(name,email,feedback)values(?,?,?)");

			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, feedback);

			ps.executeUpdate();
			ps.close();
			con.close();

			res.sendRedirect("viewFeedback.jsp");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
