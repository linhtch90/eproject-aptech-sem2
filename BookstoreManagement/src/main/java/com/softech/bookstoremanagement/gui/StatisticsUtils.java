/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softech.bookstoremanagement.gui;

import java.awt.SystemColor;
import java.io.File;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Linh
 */
public class StatisticsUtils {
    public static void drawChart(String[] dates, float[] revenueValues) throws Exception {
        final String fiat = "Revenue in a day (USD)";
//        final String audi = "AUDI";
//        final String ford = "FORD";
        final String date0 = dates[0];
        final String date1 = dates[1];
        final String date2 = dates[2];
        final String date3 = dates[3];
        final String date4 = dates[4];
        final String date5 = dates[5];
        final String date6 = dates[6];

        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(revenueValues[0], fiat, date0);
        dataset.addValue(revenueValues[1], fiat, date1);
        dataset.addValue(revenueValues[2], fiat, date2);
        dataset.addValue(revenueValues[3], fiat, date3);
        dataset.addValue(revenueValues[4], fiat, date4);
        dataset.addValue(revenueValues[5], fiat, date5);
        dataset.addValue(revenueValues[6], fiat, date6);

//        dataset.addValue(5.0, audi, speed);
//        dataset.addValue(6.0, audi, userrating);
//        dataset.addValue(10.0, audi, millage);
//        dataset.addValue(4.0, audi, safety);
//
//        dataset.addValue(4.0, ford, speed);
//        dataset.addValue(2.0, ford, userrating);
//        dataset.addValue(3.0, ford, millage);
//        dataset.addValue(6.0, ford, safety);
        JFreeChart barChart = ChartFactory.createBarChart(
                "REVENUE OF THE LAST 7 DAYS",
                "", "USD",
                dataset, PlotOrientation.VERTICAL,
                true, true, false);

        CategoryPlot cplot = (CategoryPlot) barChart.getPlot();
        cplot.setBackgroundPaint(SystemColor.inactiveCaption);//change background color

        //set  bar chart color
        ((BarRenderer) cplot.getRenderer()).setBarPainter(new StandardBarPainter());

        BarRenderer r = (BarRenderer) barChart.getCategoryPlot().getRenderer();
        r.setSeriesPaint(0, SystemColor.ORANGE);
        
        CategoryAxis xAxis = (CategoryAxis)cplot.getDomainAxis();
        xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);

        int width = 548;
        /* Width of the image */
        int height = 395;
        /* Height of the image */
        File BarChart = new File("chart/BarChart.jpeg");
        ChartUtilities.saveChartAsJPEG(BarChart, barChart, width, height);
    }
}
