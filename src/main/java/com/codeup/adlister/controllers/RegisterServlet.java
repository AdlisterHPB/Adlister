package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {


        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String passwordConfirmation = request.getParameter("confirm_password");

        if (DaoFactory.getUsersDao().findByUsername(username) != null && DaoFactory.getUsersDao().findByEmail(email) != null) {
            PrintWriter out2 = response.getWriter();
            out2.println("<script type=\"text/javascript\">");
            out2.println("alert('Username and Email already exists!');");
            out2.println("location='register';");
            out2.println("</script>");
        } else if (DaoFactory.getUsersDao().findByUsername(username) != null) {
        PrintWriter out = response.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Username already exists!');");
            out.println("location='register';");
            out.println("</script>");

        }  else if (DaoFactory.getUsersDao().findByEmail(email) != null) {
            PrintWriter out1 = response.getWriter();
            out1.println("<script type=\"text/javascript\">");
            out1.println("alert('Email already exists!');");
            out1.println("location='register';");
            out1.println("</script>");
        }
        // validate input
        boolean inputHasErrors = username.isEmpty()
            || email.isEmpty()
            || password.isEmpty()
            || (! password.equals(passwordConfirmation));

        if (inputHasErrors) {
            response.sendRedirect("/register");
            return;
        }


        // create and save a new user
        User user = new User(username, email, password);
        DaoFactory.getUsersDao().insert(user);
        request.getSession().setAttribute("user",user);
        response.sendRedirect("/login");
    }
}
