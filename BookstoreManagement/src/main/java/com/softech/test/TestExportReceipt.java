/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softech.test;

import com.softech.bookstoremanagement.database.dao.ReceiptItemsDao;
import com.softech.bookstoremanagement.database.dao.ReceiptsDao;
import com.softech.bookstoremanagement.database.dao.UsersDao;
import com.softech.bookstoremanagement.database.models.ReceiptItems;
import com.softech.bookstoremanagement.database.models.Receipts;
import com.softech.bookstoremanagement.database.models.Users;
import com.softech.bookstoremanagement.gui.PrintReceiptPdf;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Linh
 */
public class TestExportReceipt {

    public static void main(String[] args) {
        ReceiptsDao receiptsDao = new ReceiptsDao();
        Receipts receipt = new Receipts();

        ReceiptItemsDao receiptItemsDao = new ReceiptItemsDao();
        List<ReceiptItems> receiptItems = new ArrayList<>();

        UsersDao usersDao = new UsersDao();
        Users user = new Users();

        String receiptId = "2a75b727-8553-4b16-b5b7-2785539b16c3";
        try {
            receipt = receiptsDao.searchReceiptById(receiptId);
            receiptItems = receiptItemsDao.searchReceiptItemsByReceiptId(receiptId);
            user = usersDao.searchByUserId(receipt.getUserId());
            
            PrintReceiptPdf printReceiptPdf = new PrintReceiptPdf(receipt, receiptItems, user);
            printReceiptPdf.exportReceipt();
        } catch (IOException ex) {
//            Logger.getLogger(TestExportReceipt.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        } catch (SQLException ex) {
//            Logger.getLogger(TestExportReceipt.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

}
