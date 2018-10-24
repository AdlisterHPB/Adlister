package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "controllers.DeleteAdsServlet", urlPatterns = "/deleteAds")
public class DeleteAdsServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User) request.getSession().getAttribute("user");
        Ad ad = (Ad) request.getSession().getAttribute("ad");
        PrintWriter out = response.getWriter();


        if(ad.getUserId()!= user.getId()) {
            out.println("<script>alert('You cannot delete an add from another user!');location='/profile'</script>");
        } else {
            DaoFactory.getAdsDao().deleteAd(ad);
            response.sendRedirect("/profile");
        }
    }
}