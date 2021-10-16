/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softech.bookstoremanagement.database.models;

import java.time.LocalDateTime;

/**
 *
 * @author Linh
 */
public class Receipts {
    private String receiptId;
    private String userId;
    private LocalDateTime createdOn;
    private float totalPrice;
    private String status;
    

    public Receipts() {
    }

    public Receipts(String receiptId, String userId, LocalDateTime createdOn, float totalPrice, String status) {
        this.receiptId = receiptId;
        this.userId = userId;
        this.createdOn = createdOn;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public String getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(String receiptId) {
        this.receiptId = receiptId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }    
    
}
