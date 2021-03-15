package com.techelevator;

import com.techelevator.view.SalesReport;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Inventory {





    //split line of text from inventory file into an array
    public static String[] prepareItem(String itemString) {
        String[] itemArray;
        itemArray = itemString.split("\\|");
        return itemArray;
    }

    //take name of inventory file to prepare inventory
    public static ArrayList<VendingMachineItem> prepareInventory(String inventoryFile){
        Path myPath = Path.of(inventoryFile);
        ArrayList<VendingMachineItem> inventory = new ArrayList<>();
      try  (Scanner fileReader = new Scanner(myPath)){

          while (fileReader.hasNextLine()){
              //call prepareItem() on each line of the file
              String line = fileReader.nextLine();
              String[] itemData = prepareItem(line);
              //create VendingMachineItem from each array
              VendingMachineItem item = new VendingMachineItem(itemData);
              //add each VendingMachineItem to inventory list
              inventory.add(item);
          }

      } catch (IOException e) {
         System.out.println("Inventory list not found");
      }
        return inventory;
    }

}
