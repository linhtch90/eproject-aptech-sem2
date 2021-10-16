/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softech.test;

import com.softech.bookstoremanagement.database.dao.UsersDao;
import com.softech.bookstoremanagement.database.models.Users;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author Linh
 */
public class TestUsersDao {

    /*
    Create manager
     */
    public static void main(String[] args) throws IOException, SQLException {
        UsersDao usersDao = new UsersDao();
        Users user = new Users();
        user.setUserId("M01");
        user.setUsername("manager");
        user.setPassword("manager");
        user.setFirstName("John");
        user.setLastName("White");
        user.setEmail("johnw@gmail.com");
        user.setPhone("6127329982");
        user.setRole("manager");
        usersDao.insertUser(user);
    }

//    public static void main(String[] args) {
//        UsersDao usersDao = new UsersDao();
//        try {
//            boolean[] results = usersDao.validatePassword("manager", "manager", "cashier");
//            System.out.println("Valid Password: " + results[0]);
//            System.out.println("Valid Data: " + results[1]);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//
//    }

}
