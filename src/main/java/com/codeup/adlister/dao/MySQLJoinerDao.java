package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.Category;
import com.codeup.adlister.models.Joiner;
import com.codeup.adlister.models.User;
import com.mysql.jdbc.Driver;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLJoinerDao implements Joiners {
    private Connection connection = null;

    public MySQLJoinerDao(Config config) {
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
    public List<Joiner> findJoiners(long category_id) {
        String query = "SELECT * FROM joiner WHERE category_id LIKE ?";
        String searchWithWildcards = "%" + category_id + "%";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, searchWithWildcards);
            ResultSet rs = stmt.executeQuery();
            return createJoinerFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error finding ads", e);
        }
    }
    public Long insert(long ad_id, Category category){
        String query = "INSERT INTO Joiner (ad_id, category_id) VALUES (?,?)";
        try{
            PreparedStatement stmt = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1,ad_id);
            stmt.setLong(2,category.getId());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return null;
        } catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Error inserting into joiner");
        }
    }
    public void update(long ad_id, Category category, long user_id){
        String query = "UPDATE joiner LEFT JOIN Ads a ON a.id = joiner.ad_id SET ad_id = ?, category_id = ? WHERE a.user_id = ? and a.id = ?";
        try{
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1,ad_id);
            stmt.setLong(2,category.getId());
            stmt.setLong(3,user_id);
            stmt.setLong(4,ad_id);
            stmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Error updating joiner");
        }
    }
    public void delete(long ad_id){
        String query = "DELETE FROM joiner WHERE ad_id = ?";
        try{
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1,ad_id);
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Error deleting from joiner");
        }
    }


    @Override
    public List<Joiner> allJoiners() {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM joiner");
            ResultSet rs = stmt.executeQuery();
            return createJoinerFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all category.", e);
        }
    }
    private Joiner extractJoiner(ResultSet rs) throws SQLException {
        return new Joiner(
                rs.getLong("ad_id"),
                rs.getLong("category_id")

        );
    }
    private List<Joiner> createJoinerFromResults(ResultSet rs) throws SQLException {
        List<Joiner> joiners = new ArrayList<>();
        while (rs.next()) {
            joiners.add(extractJoiner(rs));
        }
        return joiners;
    }
}