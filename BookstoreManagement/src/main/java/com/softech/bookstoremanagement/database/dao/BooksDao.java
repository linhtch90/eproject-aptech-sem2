/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softech.bookstoremanagement.database.dao;

import com.softech.bookstoremanagement.database.models.Books;
import com.softech.bookstoremanagement.database.utils.DatabaseUtils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.ArrayUtils;

/**
 *
 * @author Linh
 */
public class BooksDao {

    public List<Books> searchAllBooks() throws IOException, SQLException {
        String sql = "select * from tbl_books";
        List<Books> books = new ArrayList<>();
        try ( Connection conn = DatabaseUtils.getConnection();  Statement stat = conn.createStatement()) {
            try ( ResultSet results = stat.executeQuery(sql)) {
                while (results.next()) {
                    Books resultBook = new Books();
                    resultBook.setBookId(results.getString(1));
                    resultBook.setTitle(results.getString(2));
                    resultBook.setAuthors(results.getString(3));
                    resultBook.setPublisher(results.getString(4));
                    resultBook.setPrice(results.getFloat(5));
                    resultBook.setCoverPhoto(results.getBytes(6));
                    books.add(resultBook);

                }
            }
        }
        return books;
    }

    public List<Books> searchById(String bookId) throws IOException, SQLException {
        String sql = "select * from tbl_books where lower(book_id) like lower(?)";
        List<Books> books = new ArrayList<>();
        try ( Connection conn = DatabaseUtils.getConnection();  PreparedStatement pstat = conn.prepareStatement(sql)) {
            pstat.setString(1, "%" + bookId + "%");
            try ( ResultSet results = pstat.executeQuery()) {
                while (results.next()) {
                    Books resultBook = new Books();
                    resultBook.setBookId(results.getString(1));
                    resultBook.setTitle(results.getString(2));
                    resultBook.setAuthors(results.getString(3));
                    resultBook.setPublisher(results.getString(4));
                    resultBook.setPrice(results.getFloat(5));
                    resultBook.setCoverPhoto(results.getBytes(6));
                    books.add(resultBook);

                }
            }
        }
        return books;
    }

    public List<Books> searchByTitle(String title) throws IOException, SQLException {
        String sql = "select * from tbl_books where lower(title) like lower(?)";
        List<Books> books = new ArrayList<>();
        try ( Connection conn = DatabaseUtils.getConnection();  PreparedStatement pstat = conn.prepareStatement(sql)) {
            pstat.setString(1, "%" + title + "%");
            try ( ResultSet results = pstat.executeQuery()) {
                while (results.next()) {
                    Books resultBook = new Books();
                    resultBook.setBookId(results.getString(1));
                    resultBook.setTitle(results.getString(2));
                    resultBook.setAuthors(results.getString(3));
                    resultBook.setPublisher(results.getString(4));
                    resultBook.setPrice(results.getFloat(5));
                    resultBook.setCoverPhoto(results.getBytes(6));
                    books.add(resultBook);

                }
            }
        }
        return books;
    }

    public List<Books> searchByAuthors(String authors) throws IOException, SQLException {
        String sql = "select * from tbl_books where lower(authors) like lower(?)";
        List<Books> books = new ArrayList<>();
        try ( Connection conn = DatabaseUtils.getConnection();  PreparedStatement pstat = conn.prepareStatement(sql)) {
            pstat.setString(1, "%" + authors + "%");
            try ( ResultSet results = pstat.executeQuery()) {
                while (results.next()) {
                    Books resultBook = new Books();
                    resultBook.setBookId(results.getString(1));
                    resultBook.setTitle(results.getString(2));
                    resultBook.setAuthors(results.getString(3));
                    resultBook.setPublisher(results.getString(4));
                    resultBook.setPrice(results.getFloat(5));
                    resultBook.setCoverPhoto(results.getBytes(6));
                    books.add(resultBook);

                }
            }
        }
        return books;
    }

    public List<Books> searchByPublisher(String publisher) throws IOException, SQLException {
        String sql = "select * from tbl_books where lower(publisher) like lower(?)";
        List<Books> books = new ArrayList<>();
        try ( Connection conn = DatabaseUtils.getConnection();  PreparedStatement pstat = conn.prepareStatement(sql)) {
            pstat.setString(1, "%" + publisher + "%");
            try ( ResultSet results = pstat.executeQuery()) {
                while (results.next()) {
                    Books resultBook = new Books();
                    resultBook.setBookId(results.getString(1));
                    resultBook.setTitle(results.getString(2));
                    resultBook.setAuthors(results.getString(3));
                    resultBook.setPublisher(results.getString(4));
                    resultBook.setPrice(results.getFloat(5));
                    resultBook.setCoverPhoto(results.getBytes(6));
                    books.add(resultBook);

                }
            }
        }
        return books;
    }

    public boolean deleteBook(String bookId) throws IOException, SQLException {
        String sql = "delete from tbl_books where book_id = ?";
        try ( Connection conn = DatabaseUtils.getConnection();  PreparedStatement pstat = conn.prepareStatement(sql)) {
            pstat.setString(1, bookId);
            return pstat.executeUpdate() > 0;
        }
    }

    public boolean insertBook(Books inputBook) throws IOException, SQLException {
        if (ArrayUtils.isEmpty(inputBook.getCoverPhoto())) {
            String sql = "insert into tbl_books(book_id, title, authors, publisher, price) values (?, ?, ?, ?, ?)";
            try ( Connection conn = DatabaseUtils.getConnection();  PreparedStatement pstat = conn.prepareStatement(sql)) {
                pstat.setString(1, inputBook.getBookId());
                pstat.setString(2, inputBook.getTitle());
                pstat.setString(3, inputBook.getAuthors());
                pstat.setString(4, inputBook.getPublisher());
                pstat.setFloat(5, inputBook.getPrice());
                
                return pstat.executeUpdate() > 0;
            }
        } else {
            String sql = "insert into tbl_books(book_id, title, authors, publisher, price, cover_photo) values (?, ?, ?, ?, ?, ?)";
            try (Connection conn = DatabaseUtils.getConnection(); PreparedStatement pstat = conn.prepareStatement(sql)) {
                pstat.setString(1, inputBook.getBookId());
                pstat.setString(2, inputBook.getTitle());
                pstat.setString(3, inputBook.getAuthors());
                pstat.setString(4, inputBook.getPublisher());
                pstat.setFloat(5, inputBook.getPrice());
                InputStream coverPhotoStream = new ByteArrayInputStream(inputBook.getCoverPhoto());
                pstat.setBinaryStream(6, coverPhotoStream);
 
                return pstat.executeUpdate() > 0;
            }
        }

    }
    
    public boolean updateBook(Books inputBook) throws IOException, SQLException {
        if (ArrayUtils.isEmpty(inputBook.getCoverPhoto())) {
            String sql = "update tbl_books set title = ?, authors = ?, publisher = ?, price = ? where book_id = ?";
            try ( Connection conn = DatabaseUtils.getConnection();  PreparedStatement pstat = conn.prepareStatement(sql)) {      
                pstat.setString(1, inputBook.getTitle());
                pstat.setString(2, inputBook.getAuthors());
                pstat.setString(3, inputBook.getPublisher());
                pstat.setFloat(4, inputBook.getPrice());
                pstat.setString(5, inputBook.getBookId());
                
                return pstat.executeUpdate() > 0;
            }
        } else {
            String sql = "update tbl_books set title = ?, authors = ?, publisher = ?, price = ?, cover_photo = ? where book_id = ?";
            try (Connection conn = DatabaseUtils.getConnection(); PreparedStatement pstat = conn.prepareStatement(sql)) {
                pstat.setString(1, inputBook.getTitle());
                pstat.setString(2, inputBook.getAuthors());
                pstat.setString(3, inputBook.getPublisher());
                pstat.setFloat(4, inputBook.getPrice());
                InputStream coverPhotoStream = new ByteArrayInputStream(inputBook.getCoverPhoto());
                pstat.setBinaryStream(5, coverPhotoStream);
                pstat.setString(6, inputBook.getBookId());
 
                return pstat.executeUpdate() > 0;
            }
        }
    }
    
    public float getBookPriceById(String inputBookId) throws IOException, SQLException {
        float bookPrice = 0F;
        String sql = "select price from tbl_books where book_id = ?";
        try (Connection conn = DatabaseUtils.getConnection(); PreparedStatement pstat = conn.prepareStatement(sql)) {
            pstat.setString(1, inputBookId);
            try (ResultSet results = pstat.executeQuery()) {
                if (results.next()) {
                    bookPrice = results.getFloat(1);
                }
            }
        }
        return bookPrice;
    }
}
