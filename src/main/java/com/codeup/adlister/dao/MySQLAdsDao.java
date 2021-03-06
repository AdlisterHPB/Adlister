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


public class MySQLAdsDao implements Ads {
    private Connection connection = null;

    public MySQLAdsDao(Config config) {
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

    @Override
    public List<Ad> all() {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM ads");
            ResultSet rs = stmt.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all ads.", e);
        }
    }

    @Override
    public Long insert(Ad ad) {
        try {
            String insertQuery = "INSERT INTO ads(user_id, title, description) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, ad.getUserId());
            stmt.setString(2, ad.getTitle());
            stmt.setString(3, ad.getDescription());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating a new ad.", e);
        }
    }

    private Ad extractAd(ResultSet rs) throws SQLException {
        long id = rs.getLong("id");
        return new Ad(
            id,
            rs.getLong("user_id"),
            rs.getString("title"),
            rs.getString("description"),
            DaoFactory.getCategoriesDao().findCategories(id)
        );
    }
    public List<Ad> getAllAdsByUser(Long userId ){
        String query = "SELECT * FROM ads WHERE user_id =  ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setLong(1,userId);
            ResultSet rs = stmt.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error finding ads", e);
        }
    }


    public List<Ad> findAds(String title) {
        String query = "SELECT a.*, c.* FROM ads a left JOIN Joiner j ON a.id = j.ad_id left JOIN Categories c ON c.id = j.category_id WHERE a.title LIKE ? or c.category like ?";
        String searchWithWildcards = "%" + title + "%";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, searchWithWildcards);
            stmt.setString(2,searchWithWildcards);
            ResultSet rs = stmt.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error finding ads", e);
        }
    }
    public Ad updateAd(Ad ad){
        String query = "UPDATE ads SET user_id = ?, title = ?, description = ? WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1,ad.getUserId());
            stmt.setString(2,ad.getTitle());
            stmt.setString(3,ad.getDescription());
            stmt.setLong(4,ad.getId());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return null;
        } catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Error Updating Ad");
        }
    }
    public Ad deleteAd(Ad ad){
        String query = "DELETE FROM ads WHERE user_id = ? AND id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1,ad.getUserId());
            stmt.setLong(2,ad.getId());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return null;
        } catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Error Deleting Ad");
        }
    }

    public Ad linkIndividualAds(Long id) {
        String query = "SELECT * FROM ads WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return extractAd(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error finding ad", e);
        }
    }

    private List<Ad> createAdsFromResults(ResultSet rs) throws SQLException {
        List<Ad> ads = new ArrayList<>();
        while (rs.next()) {
            ads.add(extractAd(rs));
        }
        return ads;
    }
}
