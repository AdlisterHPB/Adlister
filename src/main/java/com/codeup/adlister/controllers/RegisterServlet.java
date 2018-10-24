package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "controllers.RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String passwordConfirmation = request.getParameter("confirm_password");

        if (DaoFactory.getUsersDao().findByUsername(username) != null && DaoFactory.getUsersDao().findByEmail(email) != null) {
            session.setAttribute("username", username);
            session.setAttribute("email", email);
            PrintWriter out2 = response.getWriter();
            out2.println("<script type=\"text/javascript\">");
            out2.println("alert('Username and Email already exists!');");
            out2.println("location='register';");
            out2.println("</script>");
        } else if (DaoFactory.getUsersDao().findByUsername(username) != null) {
            session.setAttribute("username", username);
            session.setAttribute("email", email);
            PrintWriter out = response.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Username already exists!');");
            out.println("location='register';");
            out.println("</script>");

        }  else if (DaoFactory.getUsersDao().findByEmail(email) != null) {
            session.setAttribute("username", username);
            session.setAttribute("email", email);
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
            PrintWriter out3 = response.getWriter();
            out3.println("<script type=\"text/javascript\">");
            out3.println("alert('Missing required field!');");
            out3.println("location='register';");
            out3.println("</script>");
            return;
        }


        // create and save a new user
        User user = new User(username, email, password);
        DaoFactory.getUsersDao().insert(user);
        request.getSession().setAttribute("user",user);
        response.sendRedirect("/login");
    }
}
