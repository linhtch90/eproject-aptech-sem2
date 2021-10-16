/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softech.test;

import com.softech.bookstoremanagement.database.dao.BooksDao;
import com.softech.bookstoremanagement.database.dao.ReceiptItemsDao;
import com.softech.bookstoremanagement.database.dao.ReceiptsDao;
import com.softech.bookstoremanagement.database.models.Books;
import com.softech.bookstoremanagement.database.models.ReceiptItems;
import com.softech.bookstoremanagement.database.models.Receipts;
import com.softech.bookstoremanagement.database.utils.DatabaseUtils;
import java.io.IOException;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class TestTool {

    public static void main(String[] args) {

        ArrayList<String> listBookId = new ArrayList<>(Arrays.asList("B01", "B02", "B03", "B04", "B05", "B06", "B07", "B08", "B09", "B10", "B11", "B12", "B13", "B14", "B15", "B16", "B17", "B18", "B19", "B20"));
        ArrayList<Float> total = new ArrayList<>();       
        ArrayList<Receipts> listReceipts = new ArrayList<>();
        ArrayList<ReceiptItems> listReceiptItems = new ArrayList<>();

        Random random = new Random();

        /*Date: 03/10/2021 -> 09/10/2021, Create random (LocalDateTime & Receipt ID)*/
        int day;
        int month = 10;
        int year = 2021;

        /*For: Day*/
        for (day = 10; day <= 11; day++) {
            LocalDate targetDate = LocalDate.of(year, month, day);
            Instant instant = targetDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
            int milisecondsInDay = 24 * 60 * 60 * 1000;

            int minReceipt = 1;
            int maxReceipt = 3;
            int n = random.nextInt((maxReceipt - minReceipt) + 1) + minReceipt;

            /*For: Receipt*/
            for (int i = 1; i <= n; i++) {
                long randomMiliseconds = (long) random.nextInt(milisecondsInDay);
                long dateToMiliseconds = instant.toEpochMilli();

                long randomDateTimeMiliseconds = dateToMiliseconds + randomMiliseconds;

                LocalDateTime randomDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(randomDateTimeMiliseconds), ZoneId.systemDefault());

                Receipts receipts = new Receipts();

                receipts.setCreatedOn(randomDateTime);
                receipts.setReceiptId(DatabaseUtils.createId());
                receipts.setUserId("C01");
//                String randomStatus = arrStatus[random.nextInt(arrStatus.length)];
                receipts.setStatus("Paid");

                int minReceiptItem = 1;
                int maxReceiptItem = 5;
                int item = random.nextInt((maxReceiptItem - minReceiptItem) + 1) + minReceiptItem;
                ArrayList<String> bookItems = new ArrayList<>();

                /*For: Book Id*/
                for (int k = 0; k < item; k++) {
                    if (bookItems.size() == 0) {
                        String id = listBookId.get(new Random().nextInt(listBookId.size()));
                        bookItems.add(id);
                    } else {
                        while (true) {
                            String id = listBookId.get(new Random().nextInt(listBookId.size()));
                            if (bookItems.contains(id) == true) {
                                continue;
                            } else {
                                bookItems.add(id);
                                break;
                            }
                        }
                    }
                }

//                for (int k = 0; k < item; k++) {
//                    System.out.println("Book Id: " + bookItems.get(k));
//                }
                float sum = 0;
                /*For: Receipt Item*/
                for (int j = 0; j < item; j++) {
                    ReceiptItems receiptItems = new ReceiptItems();
                    Books book = new Books();
                    String bookId = bookItems.get(j);

                    BooksDao booksDao = new BooksDao();

                    try {
                        book = booksDao.searchById(bookId).get(0);
                    } catch (IOException ex) {
                        ex.printStackTrace();
//                        Logger.getLogger(TestTool.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
//                        Logger.getLogger(TestTool.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    float price = book.getPrice();

                    int minQuantity = 1;
                    int maxQuantity = 6;
                    int quantity = random.nextInt((maxQuantity - minQuantity) + 1) + minQuantity;

                    float totalItem = price * quantity;
                    sum = sum + totalItem;
                    total.add(totalItem);
//                    System.out.println("Price: " +price);
//                    System.out.println("Quantity: " +quantity);
//                    System.out.println("Total: " +total);
                    receiptItems.setReceiptItemId(DatabaseUtils.createId());
                    receiptItems.setBookId(bookId);
                    receiptItems.setReceiptId(receipts.getReceiptId());
                    receiptItems.setQuantity(quantity);
                    receiptItems.setItemTotalPrice(totalItem);

//                    System.out.println("Receipt Items Id: " + receiptItems.getReceiptItemId());
                    listReceiptItems.add(receiptItems);
                }

//                System.out.println("Random DateTime: " + receipts.getCreatedOn());
//                System.out.println("Receipt Id: " + receipts.getReceiptId());
//                System.out.println("Cashier: " + receipts.getUserId());
//                System.out.println("Random Status selected: " + receipts.getStatus());

                receipts.setTotalPrice(sum);

                listReceipts.add(receipts);

            }

        }

        for (int x = 0; x < listReceipts.size(); x++) {
            ReceiptsDao receiptsDao = new ReceiptsDao();
            try {
                receiptsDao.insertReceipt(listReceipts.get(x));
            } catch (IOException ex) {
                ex.printStackTrace();
//                    Logger.getLogger(TestTool.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                ex.printStackTrace();
//                    Logger.getLogger(TestTool.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        for (ReceiptItems receiptItem : listReceiptItems) {
            ReceiptItemsDao receiptItemsDao = new ReceiptItemsDao();
            try {
                receiptItemsDao.insertReceiptItem(receiptItem);
            } catch (IOException ex) {
                ex.printStackTrace();
//                        Logger.getLogger(TestTool.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                ex.printStackTrace();
//                        Logger.getLogger(TestTool.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
