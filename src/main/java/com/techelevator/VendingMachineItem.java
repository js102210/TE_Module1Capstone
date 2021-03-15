package com.techelevator;

import java.math.BigDecimal;

public class VendingMachineItem {
    private String name;
    private BigDecimal price;
    private String itemSlot;
    private String  itemType;
    private  int remainingStock;
    private int numberSold;


    public boolean isInStock(){
        return this.getRemainingStock() > 0;
    }
    public int getNumberSold(){
        return this.numberSold;
    }
    public int getRemainingStock(){
        return this.remainingStock;
    }
    public String getName(){
        return this.name;
    }
    public  BigDecimal getPrice() {
        return this.price;
    }
    public String getItemSlot(){
        return this.itemSlot;
    }
    public String getItemType(){
        return this.itemType;
    }
    public void vendItem() {
        //update stock and units sold and print expertly written marketing copy
        this.remainingStock -= 1;
        this.numberSold++;
        if (this.itemType.equals("Chip")){
            System.out.println("Crunch Crunch, Yum!");
        }
        if (this.itemType.equals("Candy")){
            System.out.println("Munch Munch, Yum!");
        }
        if (this.itemType.equals("Drink")){
            System.out.println("Crunch Crunch, Yum!");
        }
        if (this.itemType.equals("Gum")){
            System.out.println("Chew Chew, Yum!");
        }
    }
    //constructor references array to assign instance variables
    public VendingMachineItem(String[] itemArray){
        this.itemSlot = itemArray[0];
        this.name = itemArray[1];
        this.price = BigDecimal.valueOf(Double.parseDouble(itemArray[2]));
        this.itemType = itemArray[3];
        this.remainingStock = 5;
        this.numberSold = 0;

    }

}
