/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softech.test;

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
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

public class PrintPdf {

    private BaseFont bfBold;
    private BaseFont bf;
    private int pageNumber = 0;

    public static void main(String[] args) {

        String pdfFilename = "examplePdf2.pdf";
        PrintPdf generateInvoice = new PrintPdf();
//  if (args.length < 1)
//  {
//   System.err.println("Usage: java "+ generateInvoice.getClass().getName()+
//   " PDF_Filename");
//   System.exit(1);
//  }

//  pdfFilename = args[0].trim();
        generateInvoice.createPDF(pdfFilename);

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

            for (int i = 0; i < 100; i++) {
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

//    private void generateLayout(Document doc, PdfContentByte cb) {
//
//        try {
//
//            cb.setLineWidth(0.5f);
//
//            // Invoice Header box layout
//            cb.rectangle(420, 700, 150, 60);
//            cb.moveTo(420, 720);
//            cb.lineTo(570, 720);
//            cb.moveTo(420, 740);
//            cb.lineTo(570, 740);
//            cb.moveTo(480, 700);
//            cb.lineTo(480, 760);
//            cb.stroke();
//
//            // Invoice Header box Text Headings 
//            createHeadings(cb, 422, 748, "Cashier");
//            createHeadings(cb, 422, 728, "Date");
//            createHeadings(cb, 422, 708, "Total");
//
//            // Invoice Detail box layout 
//            cb.rectangle(20, 50, 550, 600);
//            cb.moveTo(20, 630);
//            cb.lineTo(570, 630);
//            cb.moveTo(50, 50);
//            cb.lineTo(50, 650);
//            cb.moveTo(150, 50);
//            cb.lineTo(150, 650);
//            cb.moveTo(430, 50);
//            cb.lineTo(430, 650);
//            cb.moveTo(500, 50);
//            cb.lineTo(500, 650);
//            cb.stroke();
//
//            // Invoice Detail box Text Headings 
//            createHeadings(cb, 22, 638, "Qty");
//            createHeadings(cb, 52, 638, "Item Number");
//            createHeadings(cb, 152, 638, "Item Description");
//            createHeadings(cb, 432, 638, "Price");
//            createHeadings(cb, 502, 638, "Ext Price");
//
//            //add the images
//            Image companyLogo = Image.getInstance("images/olympics_logo.gif");
//            companyLogo.setAbsolutePosition(25, 700);
//            companyLogo.scalePercent(25);
//            doc.add(companyLogo);
//
//        } catch (DocumentException dex) {
//            dex.printStackTrace();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//
//    }
    
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
            createHeadings(cb, 422, 728, "Date");
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
            createHeadings(cb, 472, 638, "Price");
            createHeadings(cb, 522, 638, "Ext Price");

            //add the images
            Image companyLogo = Image.getInstance("images/olympics_logo.gif");
            companyLogo.setAbsolutePosition(25, 700);
            companyLogo.scalePercent(25);
            doc.add(companyLogo);

        } catch (DocumentException dex) {
            dex.printStackTrace();
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

            createHeadings(cb, 482, 748, "C03 - Kelly Blue");
            createHeadings(cb, 482, 728, "Oct-09-2021");
            cb.setColorFill(BaseColor.RED);            
            createHeadings(cb, 482, 708, "$125.75");            
            cb.setColorFill(BaseColor.BLACK);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void generateDetail(Document doc, PdfContentByte cb, int index, int y) {
        DecimalFormat df = new DecimalFormat("0.00");
        
        try {

            createContent(cb, 22, y, String.valueOf(index + 1), PdfContentByte.ALIGN_LEFT);
            createContent(cb, 52, y, "ITEM" + String.valueOf(index + 1), PdfContentByte.ALIGN_LEFT);
            createContent(cb, 92, y, "Product Description - SIZE " + String.valueOf(index + 1), PdfContentByte.ALIGN_LEFT);

            int quantity = index * 2;
            double price = Double.valueOf(df.format(Math.random() * 10));
            double extPrice = price * (index + 1);
            createContent(cb, 468, y, String.valueOf(quantity), PdfContentByte.ALIGN_RIGHT);
            createContent(cb, 518, y, df.format(price), PdfContentByte.ALIGN_RIGHT);
            createContent(cb, 568, y, df.format(extPrice), PdfContentByte.ALIGN_RIGHT);

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
