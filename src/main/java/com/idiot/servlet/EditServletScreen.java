package com.idiot.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Screen")
public class EditServletScreen extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      Connection con = null;
      PreparedStatement pstmt = null;
      ResultSet res = null;
      String url = "jdbc:mysql:///book";
      String un = "root";
      String pwd = "root";
      resp.setContentType("text/html");
      PrintWriter writer = resp.getWriter();
      
     
      try {
    	  Class.forName("com.mysql.cj.jdbc.Driver");
    	  con = DriverManager.getConnection(url , un , pwd);
    	  String query = "SELECT BOOKNAME, BOOKEDITION, BOOKPRICE FROM bookdata WHERE id = ?";
    	  pstmt = con.prepareStatement(query);
    	  String id = req.getParameter("id");
    	  pstmt.setString(1, id);
    	  res = pstmt.executeQuery();
    	  
    	  writer.println("<form action='edit' >");

          // Create the table with input fields for Book Name, Book Edition, and Book Price
          writer.println("<table class='table table-hover'>");

          // Row for Book Name
          writer.println("<tr>");
          writer.println("<td>Book Name</td>");
          writer.println("<td><input type='text' name='bookName'></td>");
          writer.println("</tr>");

          // Row for Book Edition
          writer.println("<tr>");
          writer.println("<td>Book Edition</td>");
          writer.println("<td><input type='text' name='bookEdition'></td>");
          writer.println("</tr>");

          // Row for Book Price
          writer.println("<tr>");
          writer.println("<td>Book Price</td>");
          writer.println("<td><input type='text' name='bookPrice'></td>");
          writer.println("</tr>");

          // Row for buttons
          writer.println("<tr>");
          writer.println("<td><input type='submit' value='Edit'></td>");
          writer.println("<td><input type='reset' value='Cancel'></td>");
          writer.println("</tr>");

          writer.println("</table>");

          // Links to Home and Book List
          writer.println("<a href='home.html'>Home</a><br>");
          writer.println("<a href='booklist'>Book List</a>");

          writer.println("</form>");
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	}

}
