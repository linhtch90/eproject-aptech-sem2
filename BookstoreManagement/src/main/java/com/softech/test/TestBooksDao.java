/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softech.test;

import com.softech.bookstoremanagement.database.dao.BooksDao;
import com.softech.bookstoremanagement.database.models.Books;
import com.softech.bookstoremanagement.database.utils.DatabaseUtils;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Linh
 */
public class TestBooksDao {
//    public static void main(String[] args) throws IOException {
//        BooksDao booksDao = new BooksDao();
//        try {
//            List<Books> books = booksDao.searchAllBooks();
//            for (Books book : books) {
//                System.out.println(book.getBookId());
//            }
//        } catch (SQLException ex) {
//            for (Throwable t: ex) {
//                t.printStackTrace();
//            }
//        }
//
//    }
    
//    public static void main(String[] args) throws IOException {
//        BooksDao booksDao = new BooksDao();
//        try {
//            List<Books> books = booksDao.searchByPublisher("Springer");
//            for (Books book : books) {
//                System.out.println(book.getBookId());
//            }
//        } catch (SQLException ex) {
//            for (Throwable t: ex) {
//                t.printStackTrace();
//            }
//        }
//
//    }
    
//    public static void main(String[] args) throws IOException {
//        BooksDao booksDao = new BooksDao();
//        try {
//            List<Books> books = booksDao.searchByAuthors("Marlon Dumas, Jan Mendling");
//            for (Books book : books) {
//                System.out.println(book.getBookId());
//                System.out.println(book.getCoverPhoto().length);
//            }
//        } catch (SQLException ex) {
//            for (Throwable t: ex) {
//                t.printStackTrace();
//            }
//        }
//
//    }
    
    public static void main(String[] args) {
        BooksDao booksDao = new BooksDao();
        try {
            float price = booksDao.getBookPriceById("B10");
            System.out.println("Price: " + price);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
