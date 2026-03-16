package com.example;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class UserServlet extends HttpServlet {

protected void doPost(HttpServletRequest req,HttpServletResponse res)
throws ServletException,IOException {

    String name=req.getParameter("name");
    String email=req.getParameter("email");

    User u=new User(name,email);

    UserDAO.save(u);

    res.sendRedirect("index.jsp");
}

}
