/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softech.test;

import java.io.File;

/**
 *
 * @author Admin
 */
public class TestDeleteFileSignInInfo {

    public static void main(String[] args) {
        try {
            File file = new File("signin_info/signin_info.bin");
            if (file.delete()) {
                System.out.println(file.getName() + " is deleted!");
            } else {
                System.out.println("Delete operation is failed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
