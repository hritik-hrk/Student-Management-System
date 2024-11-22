package com.jsp.servlet.studentEMS;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/find-by-id")

public class findbyIdServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	int studentId = Integer.parseInt(req.getParameter("studentId"));
	
		
		//jdbc logic 
		
		Connection conn = null;
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet_student-management-system?user=root&password=root")	;
			
			PreparedStatement pst = conn.prepareStatement("SELECT * from student  where studentId = ?");
			
		
			pst.setInt(1,studentId);
			
			ResultSet rs = pst.executeQuery();
			req.setAttribute("resultSet",rs);
			RequestDispatcher rd = req.getRequestDispatcher("UpdateStudent.jsp");
			rd.forward(req, resp);
		
			
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		finally {
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}
		}
		
	
	
	
	}
	

}
