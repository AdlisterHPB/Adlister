package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "controllers.UpdateProfileServlet", urlPatterns = "/updateProfile")
public class UpdateProfileServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/login");
            return;
        }
        request.getRequestDispatcher("/WEB-INF/updateProfile.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user= (User) request.getSession().getAttribute("user");
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmNewPassword = request.getParameter("confirmNewPassword");
        Boolean validOldPass = BCrypt.checkpw(oldPassword,user.getPassword());
        Boolean passwordConfirmed = newPassword.equals(confirmNewPassword);
        if(email.isEmpty()){
            response.getWriter().println("<script type='text/javascript'>alert('Email field cannot be empty');location='updateProfile';</script>");

        }
        if(username.isEmpty()){
            response.getWriter().println("<script type='text/javascript'>alert('Username field Cannot be empty');location='updateProfile'</script>");

        }
        if(!validOldPass && !oldPassword.isEmpty()){
            response.getWriter().println("<script type='text/javascript'>alert('Old password is incorrect');location='updateProfile'</script>");

        }
        if(oldPassword.isEmpty() && newPassword.isEmpty() && confirmNewPassword.isEmpty() && !email.isEmpty() && !username.isEmpty()){
            user.setEmail(email);
            user.setUsername(username);
            DaoFactory.getUsersDao().updateUser(user);
            response.sendRedirect("/profile");
        }
        if(!passwordConfirmed){
            response.getWriter().println("<script type='text/javascript'>alert('new Password does not match confirmed Password');location='updateProfile';</script>");
        }
        if(validOldPass && passwordConfirmed){
            user.setEmail(email);
            user.setUsername(username);
            user.setPassword(newPassword);
            DaoFactory.getUsersDao().updateUser(user);
            response.sendRedirect("/profile");
        }
    }
}
