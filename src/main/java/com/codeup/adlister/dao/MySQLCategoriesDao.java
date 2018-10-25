package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.Category;
import com.codeup.adlister.models.User;
import com.mysql.jdbc.Driver;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLCategoriesDao implements Categories{
    private Connection connection = null;

    public MySQLCategoriesDao(Config config) {
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                    config.getUrl(),
                    config.getUser(),
                    config.getPassword()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database!", e);
        }
    }
    public List<Category> findCategories(long id) {
        String query = "SELECT * FROM Categories JOIN Joiner as j ON j.category_id = Categories.id JOIN Ads as a ON a.id = j.ad_id JOIN Users as u ON u.id=j.ad_id WHERE a.id = ?";
//        String searchWithWildcards = "%"+ id + category + "%";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setLong(1, id);
//            stmt.setString(2, category);
            ResultSet rs = stmt.executeQuery();
            return createCategoryFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error finding category", e);
        }
    }

    public Category findCategoryByName(String category){
        String query = "SELECT * FROM categories WHERE category = ?";
        try{
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1,category);
            ResultSet rs = stmt.executeQuery();
            return extractCategory(rs);
        } catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Can't find category by name");
        }
    }
    @Override
    public List<Category> allCategories() {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM categories");
            ResultSet rs = stmt.executeQuery();
            return createCategoryFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all category.", e);
        }
    }
    private Category extractCategory(ResultSet rs) throws SQLException {
        return new Category(
                rs.getLong("id"),
                rs.getString("Category")

        );
    }
    private List<Category> createCategoryFromResults(ResultSet rs) throws SQLException {
        List<Category> categories = new ArrayList<>();
        while (rs.next()) {
            categories.add(extractCategory(rs));
        }
        return categories;
    }
}
