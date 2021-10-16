/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softech.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author Linh
 */
public class CoverPhoto {
    public static Connection getConnection() throws IOException, SQLException {
        var props = new Properties();
        try ( InputStream in = Files.newInputStream(Paths.get("database.properties"))) {
            props.load(in);
        }
        String drivers = props.getProperty("jdbc.drivers");
        if (drivers != null) {
            System.setProperty("jdbc.drivers", drivers);
        }
        String url = props.getProperty("jdbc.url");
        String username = props.getProperty("jdbc.username");
        String password = props.getProperty("jdbc.password");

        return DriverManager.getConnection(url, username, password);
    }
    
    /**
     * Insert cover photos to the remote Postgresql database
     * @throws IOException
     * @throws SQLException 
     */
    public static void insertCoverPhotos() throws IOException, SQLException {
        List<String> bookIds = new ArrayList<String>(Arrays.asList("B01", "B02", "B03", "B04", "B05", "B06", "B07", "B08", "B09", "B10", "B11", "B12", "B13", "B14", "B15", "B16", "B17", "B18", "B19", "B20"));
        
        List<String> coverPhotos = new ArrayList<String>(Arrays.asList("B01.jpg", "B02.jpg", "B03.jpg", "B04.jpg", "B05.jpg", "B06.jpg", "B07.jpg", "B08.jpg", "B09.jpg", "B10.jpg", "B11.jpg", "B12.jpg", "B13.jpg", "B14.jpg", "B15.jpg", "B16.jpg", "B17.jpg", "B18.jpg", "B19.jpg", "B20.jpg"));
        
        String coverPhotoFolder = "cover_photos/";
        
        String sql = "update tbl_books set cover_photo = ? where book_id = ?";
        
        try (Connection conn = getConnection(); PreparedStatement pstate = conn.prepareStatement(sql)) {
            for (int i = 0; i < bookIds.size(); i++) {
                String bookId = bookIds.get(i);
                String coverPhoto = coverPhotos.get(i);
                File file = new File(coverPhotoFolder + coverPhoto);
                FileInputStream fis = new FileInputStream(file);                
                pstate.setBinaryStream(1, fis, file.length());
                pstate.setString(2, bookId);
                pstate.executeUpdate();
                fis.close();
            }
            
        }
    }
    
    public static void main(String[] args) throws IOException {
        try {
            insertCoverPhotos();
        } catch (SQLException ex) {
            for (Throwable t: ex) {
                t.printStackTrace();
            }
        }
    }
    
}
