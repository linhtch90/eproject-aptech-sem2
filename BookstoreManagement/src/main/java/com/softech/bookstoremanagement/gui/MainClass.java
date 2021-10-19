/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softech.bookstoremanagement.gui;

import com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatLightFlatIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatNordIJTheme;
import com.softech.bookstoremanagement.database.models.Users;
import java.awt.EventQueue;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.UIManager;

import javax.swing.UIManager;

import javax.swing.UIManager;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

/**
 *
 * @author Linh
 */
public class MainClass {

    private static Users userInfo = null;
    private static String userInfoFilePath = "signin_info/signin_info.bin";
    private static String themeConfigFilePath = "theme.properties";

    public static Users readUserInfo(String userInfoFilePath) {
//        System.out.println("This is readUserInfo");
        Users userInfo = null;
        try ( FileInputStream f = new FileInputStream(new File(userInfoFilePath));  ObjectInputStream o = new ObjectInputStream(f)) {
            userInfo = (Users) o.readObject();
        } catch (FileNotFoundException ex) {
//            Logger.getLogger(SignInUserInfo.class.getName()).log(Level.SEVERE, null, ex);
            userInfo = null;
            ex.printStackTrace();
        } catch (IOException ex) {
//            Logger.getLogger(SignInUserInfo.class.getName()).log(Level.SEVERE, null, ex);
            userInfo = null;
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(SignInUserInfo.class.getName()).log(Level.SEVERE, null, ex);
            userInfo = null;
            ex.printStackTrace();
        }
        return userInfo;
    }

    /*
    Check user info before running app
     */
    private static void initApp() {
        if (userInfo == null) {
            new SignIn().setVisible(true);
        } else if (userInfo.getRole().equals("manager")) {
            new ManagerMain().setVisible(true);
        } else if (userInfo.getRole().equals("cashier")) {
            new CashierMain().setVisible(true);
        }
    }

    private static String getThemeConfig() {
        String storedTheme = "Light";
        Configurations configs = new Configurations();
        try {
            Configuration config = configs.properties(new File(themeConfigFilePath));
            storedTheme = config.getString("theme");
        } catch (ConfigurationException ex) {
//            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return storedTheme;
    }

    private static void runSignIn() {
        new SignIn().setVisible(true);
    }

    public static void main(String[] args) {
        userInfo = readUserInfo(userInfoFilePath);
        /*
        Set look and feel
         */
        String storedTheme = getThemeConfig();

        try {
            if (storedTheme.equals("Dark")) {
                UIManager.setLookAndFeel(new FlatNordIJTheme());
            } else if (storedTheme.equals("Light")) {
                UIManager.setLookAndFeel(new FlatArcOrangeIJTheme());
            }
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }


        /*
        Show sign in dialog
         */
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                initApp();
            }
        });
    }
}
