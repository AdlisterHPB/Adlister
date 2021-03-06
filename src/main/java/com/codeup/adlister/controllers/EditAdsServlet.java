package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.Category;
import com.codeup.adlister.models.User;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "controllers.EditAdsServlet", urlPatterns = "/editAds")
public class EditAdsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/login");
            return;
        }
        long id = Long.parseLong(request.getParameter("id"));
        request.getSession().setAttribute("ad", DaoFactory.getAdsDao().linkIndividualAds(id));
        request.getSession().setAttribute("categories", DaoFactory.getCategoriesDao().allCategories());
        request.getRequestDispatcher("/WEB-INF/ads/editDeleteAds.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User) request.getSession().getAttribute("user");
        Ad ad= (Ad) request.getSession().getAttribute("ad");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String category1 = request.getParameter("Category1");
        String category2 = request.getParameter("Category2");
        String category3 = request.getParameter("Category3");
        PrintWriter out = response.getWriter();

        if(title.isEmpty() && description.isEmpty()){
            out.println("<script>alert('Title and Description cannot be empty');location='/editAds?id=" + ad.getId() + "';</script>");
        } else if(title.isEmpty()){
            out.println("<script>alert('Title cannot be empty');location='/editAds?id=" + ad.getId() + "';</script>");

        } else if(description.isEmpty()) {
            out.println("<script>alert('Description cannot be empty');location='/editAds?id=" + ad.getId() + "';</script>");
        } else if(ad.getUserId()!= user.getId()) {
            out.println("<script>alert('You cannot edit an Ad from another user!');location='/profile'</script>");
        } else {
            if(category1 != null){
                Category firstCategory = DaoFactory.getCategoriesDao().findCategoryByName(category1);
                DaoFactory.getJoinersDao().update(ad.getId(),firstCategory,user.getId());
            }
            if(category2 != null){
                Category secondCategory = DaoFactory.getCategoriesDao().findCategoryByName(category2);
                DaoFactory.getJoinersDao().update(ad.getId(),secondCategory,user.getId());
            }
            if(category3 != null){
                Category thirdCategory = DaoFactory.getCategoriesDao().findCategoryByName(category3);
                DaoFactory.getJoinersDao().update(ad.getId(),thirdCategory,user.getId());
            }
            ad.setCategories(DaoFactory.getCategoriesDao().findCategories(ad.getId()));
            ad.setUserId(user.getId());
            ad.setTitle(title);
            ad.setDescription(description);
            DaoFactory.getAdsDao().updateAd(ad);
            response.sendRedirect("/adPage?id=" + ad.getId());
        }
    }
}

