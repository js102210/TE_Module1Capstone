package com.techelevator.view;

import com.techelevator.VendingMachineItem;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SalesReport {
    private BigDecimal totalSales = BigDecimal.ZERO;

    public void logSale(VendingMachineItem item){
        BigDecimal amountOfSale = item.getPrice();
        this.totalSales = this.totalSales.add(amountOfSale);

    }

    public void runReport(ArrayList<VendingMachineItem> currentInventory){
        try (PrintWriter salesLogger = new PrintWriter("Logs/SalesReport.txt")){
         for(VendingMachineItem item : currentInventory){
             salesLogger.println(item.getName()+"|"+ item.getNumberSold());
             totalSales = totalSales.add(item.getPrice().multiply(BigDecimal.valueOf(item.getNumberSold())));

         } salesLogger.println("\n**TOTAL SALES** "+ NumberFormat.getCurrencyInstance(Locale.US).format(this.totalSales));


        }catch (IOException e){
            System.out.println("Could not run sales report");
        }
    }
}
