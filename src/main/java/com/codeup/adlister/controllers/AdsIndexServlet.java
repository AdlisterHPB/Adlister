package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.AdsIndexServlet", urlPatterns = "/ads")
public class AdsIndexServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setAttribute("categories", DaoFactory.getCategoriesDao().allCategories());
        request.setAttribute("joiners", DaoFactory.getJoinersDao().allJoiners());
        request.setAttribute("categories", DaoFactory.getCategoriesDao().findCategories(2,"2"));
        System.out.println("TESTTESTTEST" + DaoFactory.getCategoriesDao().findCategories(2,"2"));
        request.setAttribute("ads", DaoFactory.getAdsDao().all());
        request.getRequestDispatcher("/WEB-INF/ads/index.jsp").forward(request, response);
    }
}
