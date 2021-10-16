/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softech.test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Random;

/**
 *
 * @author Linh
 */
public class TestRandomDateTime {
    public static void main(String[] args) {
        Random random = new Random();
        int milisecondsInDay = 24*60*60*1000;
        long randomMiliseconds = (long)random.nextInt(milisecondsInDay);
        System.out.println("Random miliseconds: " + randomMiliseconds);
        
        LocalDate targetDate = LocalDate.of(2021, 10, 9);
        Instant instant = targetDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
        long dateToMiliseconds = instant.toEpochMilli();
        System.out.println("Date to miliseconds: " + dateToMiliseconds);
        
        long randomDateTimeMiliseconds = dateToMiliseconds + randomMiliseconds;
        System.out.println("Random DateTime: " + randomDateTimeMiliseconds);
        
        LocalDateTime randomDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(randomDateTimeMiliseconds), ZoneId.systemDefault());
        System.out.println("Random DateTime: " + randomDateTime);
        
    }
    
}
