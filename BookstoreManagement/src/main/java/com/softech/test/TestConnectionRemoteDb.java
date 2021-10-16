/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softech.test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 *
 * @author Linh
 */
public class TestConnectionRemoteDb {

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
     * Test 1: Insert a record to test_table
     *
     * @throws IOException
     * @throws SQLException
     */
    public static void runTest1() throws IOException, SQLException {
        try ( Connection conn = getConnection();  Statement stat = conn.createStatement()) {
            String sql = "insert into test_table (id, name) values ('E02', 'Phuong')";
            stat.executeUpdate(sql);
        }
    }

    /**
     * Test 2: Print out data in test_table
     *
     * @throws java.io.IOException
     * @throws java.sql.SQLException
     */
    public static void runTest2() throws IOException, SQLException {
        try ( Connection conn = getConnection();  Statement stat = conn.createStatement()) {
            String sql = "select * from test_table";
            try ( ResultSet result = stat.executeQuery(sql)) {
                while (result.next()) {
                    System.out.println(result.getString(1) + " - " + result.getString(2));
                }
            }

        }

    }

    public static void main(String[] args) throws IOException {
//        System.out.println(System.getProperty("user.dir"));
        try {
            runTest2();
        } catch (SQLException ex) {
            for (Throwable t : ex) {
                t.printStackTrace();
            }
        }
    }

}
