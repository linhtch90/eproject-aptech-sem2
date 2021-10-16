/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softech.bookstoremanagement.database.models;

/**
 *
 * @author Linh
 */
public class ReceiptItems {
    private String receiptItemId;
    private String receiptId;
    private String bookId;
    private int quantity;
    private float itemTotalPrice;

    public ReceiptItems() {
    }

    public ReceiptItems(String receiptItemId, String receiptId, String bookId, int quantity, float itemPrice, float itemTotalPrice) {
        this.receiptItemId = receiptItemId;
        this.receiptId = receiptId;
        this.bookId = bookId;
        this.quantity = quantity;
        this.itemTotalPrice = itemTotalPrice;
    }

    public String getReceiptItemId() {
        return receiptItemId;
    }

    public void setReceiptItemId(String receiptItemId) {
        this.receiptItemId = receiptItemId;
    }

    public String getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(String receiptId) {
        this.receiptId = receiptId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getItemTotalPrice() {
        return itemTotalPrice;
    }

    public void setItemTotalPrice(float itemTotalPrice) {
        this.itemTotalPrice = itemTotalPrice;
    }
    
}
