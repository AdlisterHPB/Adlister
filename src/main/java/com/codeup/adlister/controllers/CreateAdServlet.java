package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.Category;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "controllers.CreateAdServlet", urlPatterns = "/ads/create")
public class CreateAdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("categories", DaoFactory.getCategoriesDao().allCategories());
        request.getRequestDispatcher("/WEB-INF/ads/create.jsp")
            .forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getSession().getAttribute("user") != null) {
            User user = (User) request.getSession().getAttribute("user");
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            String category1 = request.getParameter("Category1");
            String category2 = request.getParameter("Category2");
            String category3 = request.getParameter("Category3");
            System.out.println(category1);
            System.out.println(category2);
            System.out.println(category3);
            PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

            if(title.isEmpty() && description.isEmpty()){
                session.setAttribute("title", title);
                session.setAttribute("description", description);
                out.println("<script>alert('You cannot create a blank ad!');location='/ads/create'</script>");
            } else if(title.isEmpty()){
                session.setAttribute("title", title);
                session.setAttribute("description", description);
                out.println("<script>alert('Title cannot be blank!');location='/ads/create'</script>");
            } else if(description.isEmpty()){
                session.setAttribute("title", title);
                session.setAttribute("description", description);
                out.println("<script>alert('Description cannot be blank!');location='/ads/create'</script>");
            } else {
                Ad ad = new Ad(
                        user.getId(),
                        title,
                        description
                );
                DaoFactory.getAdsDao().insert(ad);
                if(category1 != null){
                   Category firstCategory = DaoFactory.getCategoriesDao().findCategoryByName(category1);
                   DaoFactory.getJoinersDao().insert(ad.getId(),firstCategory.getId());
                }
                response.sendRedirect("/ads");
            }
        } else {
            response.sendRedirect("/login");
        }
    }
}
