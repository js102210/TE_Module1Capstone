package com.techelevator;

import com.techelevator.view.Customer;
import com.techelevator.view.MenuDrivenCLI;
import com.techelevator.view.SalesReport;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import static com.techelevator.Inventory.prepareInventory;


public class Application {
//populate menu options
    private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Items";
    private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase Items";
    private static final String MENU_OPTION_QUIT = "Exit";
    private static final String MENU_OPTION_SALES_REPORT = "Display Sales Report";
    private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MENU_OPTION_QUIT, MENU_OPTION_SALES_REPORT};
    private static final String MENU_PURCHASE_ITEM = "Select Product";
    private static final String MENU_ADD_FUNDS = "Feed Money";
    private static final String MENU_FINISH_TRANSACTION = "Finish Transaction";
    private static final String[] PURCHASE_MENU_OPTIONS = {MENU_PURCHASE_ITEM, MENU_ADD_FUNDS, MENU_FINISH_TRANSACTION};

    private final MenuDrivenCLI ui = new MenuDrivenCLI();
    private ArrayList<VendingMachineItem> inventory;
    private final Customer customer = new Customer();

    public static void main(String[] args) {
        Application application = new Application(new ArrayList<VendingMachineItem>());
        application.run();
    }

    //create empty list to hold inventory
    public Application(ArrayList<VendingMachineItem> inventory) {
        this.inventory = inventory;
    }

    public void run() {
        boolean running = true;
        //populate inventory based on txt file
        this.inventory = prepareInventory("inventory.txt");
        while (running) {
            String selection = ui.promptForSelection(MAIN_MENU_OPTIONS);
            if (selection.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
                displayInventory();
            } else if (selection.equals(MAIN_MENU_OPTION_PURCHASE)) {
                handlePurchaseMenu();
            } else if (selection.equals(MENU_OPTION_QUIT)) {
                running = false;
            } else if (selection.equals(MENU_OPTION_SALES_REPORT)) {
               SalesReport currentReport = new SalesReport();
               currentReport.runReport(inventory);
            }
        }
    }

    public void buyAnItem() {
        System.out.println("Please make a selection: ");
        displayInventory();
        System.out.println("3) Exit");
        System.out.println("Your current balance is: " + NumberFormat.getCurrencyInstance(Locale.US).format(customer.getBalance()));
        //not sure if there's a better way to take input and validate it correctly
        // using the menu/menudrivencli classes
        Scanner input = new Scanner(System.in);
        VendingMachineItem selectedItem;
        boolean inMenu = true;
        while (inMenu) {
            selectedItem = null;
            //check if user input matches a key or the exit prompt (case insensitive)
            String selection = input.nextLine().toUpperCase();
            for (VendingMachineItem item : this.inventory) {
                if (selection.equals(item.getItemSlot()))
                    selectedItem = item;
            } if (selection.equals("3")){
                inMenu = false;
            }
            else if (selectedItem == null){
                System.out.println("That was an invalid selection");
            }
             else if (!selectedItem.isInStock()) {
                System.out.println("That item is out of stock.");
            } else if (!customer.hasEnoughMoney(selectedItem.getPrice())) {
                System.out.println("You have not inserted enough money for that item.");
            } else if (customer.hasEnoughMoney(selectedItem.getPrice()) && selectedItem.isInStock()) {
                performTransaction(selectedItem);
                inMenu = false;
            }
        }

    }
    public void handlePurchaseMenu(){
        boolean running = true;
        while(running){
            String selection = ui.promptForSelection(PURCHASE_MENU_OPTIONS);
            if (selection.equals(MENU_PURCHASE_ITEM)){
               buyAnItem();

            }
            if(selection.equals(MENU_ADD_FUNDS)){
                //takes number of dollars from customer as string, casts to int to verify
                //whole number input (otherwise catches NumberFormatException), then converts to
                //BigDecimal to increase customer balance
                try{Scanner moneyAdded = new Scanner (System.in);
                System.out.println("Amount to deposit (dollars only)");
                String response = moneyAdded.nextLine();
                int amountToAdd = Integer.parseInt(response);
                BigDecimal amount = BigDecimal.valueOf(amountToAdd);
                customer.feedMoney(amount);
                }catch (NumberFormatException e){
                    System.out.println("Whole numbers only");
                }
            }
            if (selection.equals(MENU_FINISH_TRANSACTION)){
                //leaving menu sets customer balance to 0 and gives change
                customer.giveChange(customer.getBalance());
                running = false;

            }

        }
    }

    public void displayInventory() {
        // read inventory list and show items for sale
        for (VendingMachineItem item : this.inventory) {
            if (item.isInStock()) {
                System.out.println(item.getItemSlot() + " " + item.getName() + NumberFormat.getCurrencyInstance(Locale.US).format(item.getPrice()));
            } else {
                System.out.println(item.getItemSlot() + " " + item.getName() + " SOLD OUT");
            }
        }
    }


    public void performTransaction(VendingMachineItem item) {
        //reduce customer's balance by item price, reduce count of item by 1
        // and display expertly crafted marketing copy
        customer.buyItem(item);
        item.vendItem();
    }


}

