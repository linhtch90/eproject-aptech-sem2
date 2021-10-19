/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softech.bookstoremanagement.database.dao;

import com.softech.bookstoremanagement.database.models.Users;
import com.softech.bookstoremanagement.database.utils.DatabaseUtils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Admin
 */
public class UsersDao {

    public List<Users> searchAllUsers() throws IOException, SQLException {
        String sql = "select * from tbl_users";
        List<Users> users = new ArrayList<>();
        try ( Connection conn = DatabaseUtils.getConnection();  Statement stat = conn.createStatement()) {
            try ( ResultSet results = stat.executeQuery(sql)) {
                while (results.next()) {
                    Users resultUser = new Users();
                    resultUser.setUserId(results.getString(1));
                    resultUser.setUsername(results.getString(2));
                    resultUser.setFirstName(results.getString(4));
                    resultUser.setLastName(results.getString(5));
                    resultUser.setEmail(results.getString(6));
                    resultUser.setPhone(results.getString(7));
                    resultUser.setRole(results.getString(8));

                    users.add(resultUser);
                }
            }
        }
        return users;
    }
    
    public Users searchByUsername(String username) throws IOException, SQLException {
        String sql = "select * from tbl_users where lower(username) = lower(?)";
        Users resultUser = new Users();
        
        try (Connection conn = DatabaseUtils.getConnection(); PreparedStatement pstat = conn.prepareStatement(sql)) {
            pstat.setString(1, username);
            try (ResultSet results = pstat.executeQuery()) {
                if (results.next()) {
                    resultUser.setUserId(results.getString(1));
                    resultUser.setUsername(results.getString(2));
                    resultUser.setFirstName(results.getString(4));
                    resultUser.setLastName(results.getString(5));
                    resultUser.setEmail(results.getString(6));
                    resultUser.setPhone(results.getString(7));
                    resultUser.setRole(results.getString(8));
                }
            }
            
        }
        return resultUser;
    }
    
    public Users searchByUserId(String userId) throws IOException, SQLException {
        String sql = "select * from tbl_users where lower(user_id) = lower(?)";
        Users resultUser = new Users();
        
        try (Connection conn = DatabaseUtils.getConnection(); PreparedStatement pstat = conn.prepareStatement(sql)) {
            pstat.setString(1, userId);
            try (ResultSet results = pstat.executeQuery()) {
                if (results.next()) {
                    resultUser.setUserId(results.getString(1));
                    resultUser.setUsername(results.getString(2));
                    resultUser.setFirstName(results.getString(4));
                    resultUser.setLastName(results.getString(5));
                    resultUser.setEmail(results.getString(6));
                    resultUser.setPhone(results.getString(7));
                    resultUser.setRole(results.getString(8));
                }
            }
            
        }
        return resultUser;
    }

    public boolean[] validatePassword(String username, String password, String role) throws IOException, SQLException {
        boolean validPassword = false;
        boolean validData = false;
        String hashedPassword = "";
        String sql = "select password from tbl_users where username = ? and role = ?";
        try ( Connection conn = DatabaseUtils.getConnection();  PreparedStatement pstat = conn.prepareStatement(sql)) {
            pstat.setString(1, username);
            pstat.setString(2, role);
            try ( ResultSet results = pstat.executeQuery()) {
                if (results.next()) {
                    hashedPassword = results.getString(1);
                }
                validData = true;
            }
        } catch (Exception e) {
            validData = false;
        }
        try {
            if (BCrypt.checkpw(password, hashedPassword)) {
                validPassword = true;
            } else {
                validPassword = false;
            }
        } catch (Exception ex) {
            validData = false;
            validPassword = false;
        }

        boolean[] outputArray = {validPassword, validData};
        return outputArray;
    }

    public boolean deleteUser(String userId) throws IOException, SQLException {
        String sql = "delete from tbl_users where user_id = ?";
        try ( Connection conn = DatabaseUtils.getConnection();  PreparedStatement pstat = conn.prepareStatement(sql)) {
            pstat.setString(1, userId);
            return pstat.executeUpdate() > 0;
        }
    }

    public boolean insertUser(Users inputUser) throws IOException, SQLException {
        String hashedPassword = BCrypt.hashpw(inputUser.getPassword(), BCrypt.gensalt());
        inputUser.setPassword(hashedPassword);
        String sql = "insert into tbl_users (user_id, username, password, first_name, last_name, email, phone, role) values (?, ?, ?, ?, ?, ?, ?, ?)";
        try ( Connection conn = DatabaseUtils.getConnection();  PreparedStatement pstat = conn.prepareStatement(sql)) {
            pstat.setString(1, inputUser.getUserId());
            pstat.setString(2, inputUser.getUsername());
            pstat.setString(3, inputUser.getPassword());
            pstat.setString(4, inputUser.getFirstName());
            pstat.setString(5, inputUser.getLastName());
            pstat.setString(6, inputUser.getEmail());
            pstat.setString(7, inputUser.getPhone());
            pstat.setString(8, inputUser.getRole());
            return pstat.executeUpdate() > 0;
        }
    }

    public boolean updateUser(Users inputUser) throws IOException, SQLException {
        boolean result;
        if (StringUtils.isNotEmpty(inputUser.getPassword())) {
            String hashedPassword = BCrypt.hashpw(String.valueOf(inputUser.getPassword()), BCrypt.gensalt());
            inputUser.setPassword(hashedPassword);
            String sql = "update tbl_users set username = ?, password = ?, first_name = ?, last_name = ?, email = ?, phone = ?, role = ? where user_id = ?";
            try ( Connection conn = DatabaseUtils.getConnection();  PreparedStatement pstat = conn.prepareStatement(sql)) {
                pstat.setString(1, inputUser.getUsername());
                pstat.setString(2, inputUser.getPassword());
                pstat.setString(3, inputUser.getFirstName());
                pstat.setString(4, inputUser.getLastName());
                pstat.setString(5, inputUser.getEmail());
                pstat.setString(6, inputUser.getPhone());
                pstat.setString(7, inputUser.getRole());
                pstat.setString(8, inputUser.getUserId());
                result = pstat.executeUpdate() > 0;
            }
        } else {
            String sql = "update tbl_users set username = ?, first_name = ?, last_name = ?, email = ?, phone = ?, role = ? where user_id = ?";
            try ( Connection conn = DatabaseUtils.getConnection();  PreparedStatement pstat = conn.prepareStatement(sql)) {
                pstat.setString(1, inputUser.getUsername());
                pstat.setString(2, inputUser.getFirstName());
                pstat.setString(3, inputUser.getLastName());
                pstat.setString(4, inputUser.getEmail());
                pstat.setString(5, inputUser.getPhone());
                pstat.setString(6, inputUser.getRole());
                pstat.setString(7, inputUser.getUserId());
                result = pstat.executeUpdate() > 0;
            }

        }
        return result;

    }
}
