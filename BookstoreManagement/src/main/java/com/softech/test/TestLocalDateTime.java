/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softech.test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Linh
 */
public class TestLocalDateTime {

    public static void main(String[] args) {
        LocalDateTime time = LocalDateTime.now();
        System.out.println("Time: " + time.format(DateTimeFormatter.ISO_DATE_TIME));
    }

}
