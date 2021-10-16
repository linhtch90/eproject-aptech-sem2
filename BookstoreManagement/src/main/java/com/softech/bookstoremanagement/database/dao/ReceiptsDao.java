/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softech.bookstoremanagement.database.dao;

import com.fasterxml.uuid.Generators;
import com.softech.bookstoremanagement.database.models.Receipts;
import com.softech.bookstoremanagement.database.utils.DatabaseUtils;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Linh
 */
public class ReceiptsDao {
    
    public boolean insertReceipt(Receipts inputReceipt) throws IOException, SQLException {
        String sql = "insert into tbl_receipts (receipt_id, user_id, created_on, total_price, status) values (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseUtils.getConnection(); PreparedStatement pstat = conn.prepareStatement(sql)) {
            pstat.setString(1, inputReceipt.getReceiptId());
            pstat.setString(2, inputReceipt.getUserId());
            pstat.setObject(3, inputReceipt.getCreatedOn());
            pstat.setFloat(4, inputReceipt.getTotalPrice());
            pstat.setString(5, inputReceipt.getStatus());
            return pstat.executeUpdate() > 0;
        }
    }
    
    public boolean cancelReceiptStatus(String inputId) throws IOException, SQLException {
        String sql = "update tbl_receipts set status = ? where receipt_id = ?";
        try (Connection conn = DatabaseUtils.getConnection(); PreparedStatement pstat = conn.prepareStatement(sql)) {
            pstat.setString(1, "Cancel");
            pstat.setString(2, inputId);
            return pstat.executeUpdate() > 0;
        }
    }
    
    public boolean paidReceiptStatus(String inputId) throws IOException, SQLException {
        String sql = "update tbl_receipts set status = ? where receipt_id = ?";
        try (Connection conn = DatabaseUtils.getConnection(); PreparedStatement pstat = conn.prepareStatement(sql)) {
            pstat.setString(1, "Paid");
            pstat.setString(2, inputId);
            return pstat.executeUpdate() > 0;
        }
    }
    
    public Receipts searchReceiptById(String inputId) throws IOException, SQLException {
        Receipts foundReceipt = new Receipts();
        String sql = "select * from tbl_receipts where receipt_id = ?";
        try (Connection conn = DatabaseUtils.getConnection(); PreparedStatement pstat = conn.prepareStatement(sql)) {
            pstat.setString(1, inputId);
            try (ResultSet results = pstat.executeQuery()) {
                if (results.next()) {
                    foundReceipt.setReceiptId(results.getString(1));
                    foundReceipt.setUserId(results.getString(2));
                    foundReceipt.setCreatedOn(results.getObject(3, LocalDateTime.class));
                    foundReceipt.setTotalPrice(results.getFloat(4));
                    foundReceipt.setStatus(results.getString(5));                    
                }
            }
        }
        return foundReceipt;
    }
    
    public List<Receipts> searchAllReceipts() throws IOException, SQLException {
        List<Receipts> allReceipts = new ArrayList<>();        
        String sql = "select * from tbl_receipts";
        try (Connection conn = DatabaseUtils.getConnection(); Statement stat = conn.createStatement()) {
            try (ResultSet results = stat.executeQuery(sql)) {
                while (results.next()) {
                    Receipts receipt = new Receipts();
                    receipt.setReceiptId(results.getString(1));
                    receipt.setUserId(results.getString(2));
                    receipt.setCreatedOn(results.getObject(3, LocalDateTime.class));
                    receipt.setTotalPrice(results.getFloat(4));
                    receipt.setStatus(results.getString(5));
                    allReceipts.add(receipt);
                }
            }
        }
        return allReceipts;
    }
    
}
