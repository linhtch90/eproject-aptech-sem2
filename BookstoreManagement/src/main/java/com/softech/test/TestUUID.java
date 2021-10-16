/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softech.test;

import com.fasterxml.uuid.Generators;
import java.util.UUID;

/**
 *
 * @author Linh
 */
public class TestUUID {
    private static String generateRandomId() {
        /*
        Create a UUID
        */  
        UUID id = Generators.randomBasedGenerator().generate();
        return id.toString();
    }
    
//    private static String generateTimeBasedId() {
//        UUID id = Generators.timeBasedGenerator().generate();
//        return id.toString();
//    }
    
    public static void main(String[] args) {
        String id = generateRandomId();
        System.out.println("ID: " + id);
    }
    
}
