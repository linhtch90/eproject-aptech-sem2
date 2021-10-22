/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softech.bookstoremanagement.gui;

//import com.google.zxing.BarcodeFormat;
//import com.google.zxing.client.j2se.MatrixToImageWriter;
//import com.google.zxing.common.BitMatrix;
//import com.google.zxing.qrcode.QRCodeWriter;
import com.softech.test.*;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Font;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 *
 * @author Linh
 */
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import com.softech.bookstoremanagement.database.dao.BooksDao;
import com.softech.bookstoremanagement.database.models.Books;
import com.softech.bookstoremanagement.database.models.ReceiptItems;
import com.softech.bookstoremanagement.database.models.Receipts;
import com.softech.bookstoremanagement.database.models.Users;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PrintReceiptPdf {

    private Receipts receipt = new Receipts();
    private List<ReceiptItems> receiptItems = new ArrayList<>();
    private Users user = new Users();

    private BaseFont bfBold;
    private BaseFont bf;
    private int pageNumber = 0;
    
    private String exportedPDFFilePath;

    public PrintReceiptPdf() {

    }

    public PrintReceiptPdf(Receipts receipt, List<ReceiptItems> receiptItems, Users user) {
        this.receipt = receipt;
        this.receiptItems = receiptItems;
        this.user = user;
    }

//    public static void main(String[] args) {
//
//        String pdfFilename = "Receipt_" + System.currentTimeMillis() + ".pdf";
//        PrintReceiptPdf generateInvoice = new PrintReceiptPdf();
//        generateInvoice.createPDF(pdfFilename);
//
//    }
    
    public void exportReceipt() {
        String pdfFilename = "Receipt_" + System.currentTimeMillis() + ".pdf";
        this.createPDF(pdfFilename);
        exportedPDFFilePath = pdfFilename;
    }
    
    public String getExportedFilePath() {
        return exportedPDFFilePath;
    }

    private void createPDF(String pdfFilename) {

        Document doc = new Document();
        PdfWriter docWriter = null;
        initializeFonts();

        try {
            String path = "exported_receipts/" + pdfFilename;
            docWriter = PdfWriter.getInstance(doc, new FileOutputStream(path));
            doc.addAuthor("Elephant Bookstore");
            doc.addCreationDate();
            doc.addProducer();
            doc.addCreator("");
            doc.addTitle("Receipt");
            doc.setPageSize(PageSize.A4);

            doc.open();
            PdfContentByte cb = docWriter.getDirectContent();

            boolean beginPage = true;
            int y = 0;

            for (int i = 0; i < receiptItems.size(); i++) {
                if (beginPage) {
                    beginPage = false;
                    generateReceiptLayout(doc, cb);
                    generateHeader(doc, cb);
                    y = 615;
                }
                generateDetail(doc, cb, i, y);
                y = y - 15;
                if (y < 50) {
                    printPageNumber(cb);
                    doc.newPage();
                    beginPage = true;
                }
            }
            printPageNumber(cb);

        } catch (DocumentException dex) {
            dex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (doc != null) {
                doc.close();
            }
            if (docWriter != null) {
                docWriter.close();
            }
        }
    }

    private void generateReceiptLayout(Document doc, PdfContentByte cb) {

        try {

            cb.setLineWidth(0.5f);

            // Invoice Header box layout
            cb.rectangle(420, 700, 150, 60);
            cb.moveTo(420, 720);
            cb.lineTo(570, 720);
            cb.moveTo(420, 740);
            cb.lineTo(570, 740);
            cb.moveTo(480, 700);
            cb.lineTo(480, 760);
            cb.stroke();

            // Invoice Header box Text Headings 
            createHeadings(cb, 422, 748, "Cashier");
            createHeadings(cb, 422, 728, "Datetime");
            createHeadings(cb, 422, 708, "Total");

            // Invoice Detail box layout 
            cb.rectangle(20, 50, 550, 600);
            cb.moveTo(20, 630);
            cb.lineTo(570, 630);
            cb.moveTo(50, 50);
            cb.lineTo(50, 650);
            cb.moveTo(90, 50);
            cb.lineTo(90, 650);
            cb.moveTo(420, 50);
            cb.lineTo(420, 650);
            cb.moveTo(470, 50);
            cb.lineTo(470, 650);
            cb.moveTo(520, 50);
            cb.lineTo(520, 650);
            cb.stroke();

            // Invoice Detail box Text Headings 
            createHeadings(cb, 22, 638, "No.");
            createHeadings(cb, 52, 638, "Book ID");
            createHeadings(cb, 92, 638, "Title");
            createHeadings(cb, 422, 638, "Quantity");
            createHeadings(cb, 472, 638, "$/volume");
            createHeadings(cb, 522, 638, "Price ($)");            
            
            BarcodeQRCode qrCodeReceipt = new BarcodeQRCode(receipt.getReceiptId(), 125, 125, null);
            Image qrCodeReceiptImage = qrCodeReceipt.getImage();
            qrCodeReceiptImage.setAbsolutePosition(20, 650);
            doc.add(qrCodeReceiptImage);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void generateHeader(Document doc, PdfContentByte cb) {

        try {

            createHeadings(cb, 240, 750, "Receipt Information");
            createHeadingsLarge(cb, 200, 735, "ELEPHANT BOOKSTORE");
//            createHeadings(cb, 200, 720, "Address Line 2");
//            createHeadings(cb, 200, 705, "City, State - ZipCode");
//            createHeadings(cb, 200, 690, "Country");

            String cashierInfo = user.getUserId() + " - " + user.getFirstName() + " " + user.getLastName();
            createHeadings(cb, 482, 748, cashierInfo);
            createHeadings(cb, 482, 728, String.valueOf(receipt.getCreatedOn().format(DateTimeFormatter.ISO_DATE_TIME)));
            cb.setColorFill(BaseColor.RED);
            createHeadings(cb, 482, 708, "$" + String.valueOf(receipt.getTotalPrice()));
            cb.setColorFill(BaseColor.BLACK);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    
    private static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

    private void generateDetail(Document doc, PdfContentByte cb, int index, int y) {
//        DecimalFormat df = new DecimalFormat("0.00");

        try {
            BooksDao booksDao = new BooksDao();            
            List<Books> books = booksDao.searchById(receiptItems.get(index).getBookId());
            Books book = books.get(0);
            
            String bookId = receiptItems.get(index).getBookId();
            String bookTitle = book.getTitle();            

            createContent(cb, 22, y, String.valueOf(index + 1), PdfContentByte.ALIGN_LEFT);
            createContent(cb, 52, y, bookId, PdfContentByte.ALIGN_LEFT);
            createContent(cb, 92, y, bookTitle, PdfContentByte.ALIGN_LEFT);
            
            String quantity = String.valueOf(receiptItems.get(index).getQuantity());
            String unitPrice = String.valueOf(PrintReceiptPdf.round(book.getPrice(), 2));
            String itemTotalPrice = String.valueOf(PrintReceiptPdf.round(receiptItems.get(index).getItemTotalPrice(), 2));
            
            createContent(cb, 468, y, quantity, PdfContentByte.ALIGN_RIGHT);
            createContent(cb, 518, y, unitPrice, PdfContentByte.ALIGN_RIGHT);
            createContent(cb, 568, y, itemTotalPrice, PdfContentByte.ALIGN_RIGHT);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void createHeadings(PdfContentByte cb, float x, float y, String text) {

        cb.beginText();
        cb.setFontAndSize(bfBold, 8);
        cb.setTextMatrix(x, y);
        cb.showText(text.trim());
        cb.endText();

    }

    private void createHeadingsLarge(PdfContentByte cb, float x, float y, String text) {

        cb.beginText();
        cb.setFontAndSize(bfBold, 12);
        cb.setTextMatrix(x, y);
        cb.showText(text.trim());
        cb.endText();

    }

    private void printPageNumber(PdfContentByte cb) {

        cb.beginText();
        cb.setFontAndSize(bfBold, 8);
        cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, "Page No. " + (pageNumber + 1), 570, 25, 0);
        cb.endText();

        pageNumber++;

    }

    private void createContent(PdfContentByte cb, float x, float y, String text, int align) {

        cb.beginText();
        cb.setFontAndSize(bf, 8);
        cb.showTextAligned(align, text.trim(), x, y, 0);
        cb.endText();

    }

    private void initializeFonts() {

        try {
            bfBold = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
