package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "controllers.IndividualAdServlet", urlPatterns = "/adPage")
public class IndividualAdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
//        List<Ad> ads = (List) request.getSession().getAttribute("ads");
        request.setAttribute("ad", DaoFactory.getAdsDao().linkIndividualAds(id));
        request.getRequestDispatcher("/WEB-INF/ads/individualAd.jsp").forward(request, response);
    }
}