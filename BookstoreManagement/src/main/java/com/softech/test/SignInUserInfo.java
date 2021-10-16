/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softech.test;

import com.softech.bookstoremanagement.database.models.Users;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Linh
 */
public class SignInUserInfo {

    public static void main(String[] args) {
        Users resultUser = new Users();
        String filePath = "signin_info/signin_info.bin";

        try {
            FileInputStream f = new FileInputStream(new File(filePath));
            ObjectInputStream o = new ObjectInputStream(f);
            resultUser = (Users) o.readObject();
            System.out.println("User Info: \n" + resultUser.getFirstName() + "\n" + resultUser.getLastName() + "\n" + resultUser.getEmail() + "\n");

        } catch (FileNotFoundException ex) {
//            Logger.getLogger(SignInUserInfo.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        } catch (IOException ex) {
//            Logger.getLogger(SignInUserInfo.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(SignInUserInfo.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

}
