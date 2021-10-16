/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softech.bookstoremanagement.database.dao;

import com.fasterxml.uuid.Generators;
import com.softech.bookstoremanagement.database.models.ReceiptItems;
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
public class ReceiptItemsDao {
    public boolean insertReceiptItem(ReceiptItems inputReceiptItem) throws IOException, SQLException {
        String sql = "insert into tbl_receipt_items (receipt_item_id, receipt_id, book_id, quantity, item_total_price) values (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseUtils.getConnection(); PreparedStatement pstat = conn.prepareStatement(sql)) {
            pstat.setString(1, inputReceiptItem.getReceiptItemId());
            pstat.setString(2, inputReceiptItem.getReceiptId());
            pstat.setString(3, inputReceiptItem.getBookId());
            pstat.setInt(4, inputReceiptItem.getQuantity());
            pstat.setFloat(5, inputReceiptItem.getItemTotalPrice());
            return pstat.executeUpdate() > 0;
        }
    }
    
    public List<ReceiptItems> searchReceiptItemsByReceiptId(String inputReceiptId) throws IOException, SQLException {
        List<ReceiptItems> receiptItems = new ArrayList<>();        
        String sql = "select * from tbl_receipt_items where receipt_id = ?";
        try (Connection conn = DatabaseUtils.getConnection(); PreparedStatement pstat = conn.prepareStatement(sql)) {
            pstat.setString(1, inputReceiptId);
            try (ResultSet results = pstat.executeQuery()) {
                while (results.next()) {
                    ReceiptItems item = new ReceiptItems();
                    item.setReceiptItemId(results.getString(1));
                    item.setReceiptId(results.getString(2));
                    item.setBookId(results.getString(3));
                    item.setQuantity(results.getInt(4));
                    item.setItemTotalPrice(results.getFloat(5));
                    receiptItems.add(item);
                }
            }
        }
        return receiptItems;
    }
}
