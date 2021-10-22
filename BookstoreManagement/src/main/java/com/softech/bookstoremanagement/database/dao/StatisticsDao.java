/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softech.bookstoremanagement.database.dao;

import com.softech.bookstoremanagement.database.utils.DatabaseUtils;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.HashMap;

/**
 *
 * @author Linh
 */
public class StatisticsDao {

    public HashMap<String, Integer> searchFiveBestSellingBooks() throws IOException, SQLException {
        HashMap<String, Integer> bestSellingBooks = new HashMap<>();
        String sql = "select book_id, sum(quantity) as sold_copies from tbl_receipt_items join tbl_receipts on (tbl_receipt_items.receipt_id = tbl_receipts.receipt_id and tbl_receipts.status = 'Paid') group by book_id order by sold_copies desc limit 5";
        try ( Connection conn = DatabaseUtils.getConnection();  Statement stat = conn.createStatement()) {
            try ( ResultSet results = stat.executeQuery(sql)) {
                while (results.next()) {
                    String bookId = results.getString(1);
                    Integer sumQuantity = results.getInt(2);
                    bestSellingBooks.put(bookId, sumQuantity);
                }
            }
        }
        return bestSellingBooks;
    }

    public HashMap<LocalDate, Float> searchRevenueLast7Days() throws IOException, SQLException {
        HashMap<LocalDate, Float> revenueLast7Days = new HashMap<>();
        String sql = "select date(created_on) as last_seven_days, sum(total_price) as total_revenue from tbl_receipts where status = 'Paid' group by last_seven_days order by last_seven_days desc limit 7";
        try ( Connection conn = DatabaseUtils.getConnection();  Statement stat = conn.createStatement()) {
            try ( ResultSet results = stat.executeQuery(sql)) {
                while (results.next()) {
                    LocalDate date = results.getObject(1, LocalDate.class);
                    Float revenue = results.getFloat(2);
                    revenueLast7Days.put(date, revenue);
                }
            }
        }
        return revenueLast7Days;
    }
}
