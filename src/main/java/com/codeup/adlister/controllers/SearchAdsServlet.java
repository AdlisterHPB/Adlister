package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(name = "controllers.SearchAdsServlet", urlPatterns = "/search")
public class SearchAdsServlet extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("search");
        request.getSession().setAttribute("ads",DaoFactory.getAdsDao().findAds(title));
        request.getRequestDispatcher("/WEB-INF/ads/index.jsp").forward(request, response);
    }

}
