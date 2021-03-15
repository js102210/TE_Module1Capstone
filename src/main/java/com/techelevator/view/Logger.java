package com.techelevator.view;

import com.techelevator.VendingMachineItem;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Currency;
import java.util.Locale;

public class Logger {
    public static void log(String name, BigDecimal amount1, BigDecimal amount2){
        try(FileOutputStream stream = new FileOutputStream("logs/Log.txt", true); PrintWriter writer = new PrintWriter(stream)){
            LocalDateTime timeStamp = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/y hh:mm");
            String formatedAmount1 = NumberFormat.getCurrencyInstance(Locale.US).format(amount1);
            String formatedAmount2 = NumberFormat.getCurrencyInstance(Locale.US).format(amount2);

            writer.println(timeStamp.format(formatter) + " " + name + " " + formatedAmount1 + " " + formatedAmount2);


        }catch (Exception e){
            System.out.println("Error no log made");
        }

    }
}
