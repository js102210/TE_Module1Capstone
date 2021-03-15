package com.techelevator.view;

import com.techelevator.VendingMachineItem;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class Customer {
    public BigDecimal balance = BigDecimal.ZERO;

    public BigDecimal getBalance(){
        return this.balance;
    }
    public void feedMoney(BigDecimal amount) {

            if (amount.compareTo(BigDecimal.ZERO) == -1) {
                //prevent negative input
                System.out.println("Not a valid input.");
            } else {
                BigDecimal newAmount = this.balance.add(amount);
                this.balance = newAmount;
                System.out.println("Your current balance is: " + NumberFormat.getCurrencyInstance(Locale.US).format(this.getBalance()));
                Logger.log("FEED MONEY", amount, this.balance);
            }
        }

    public void buyItem(VendingMachineItem itemBought){
        BigDecimal newAmount = balance.subtract(itemBought.getPrice());
        this.balance = newAmount;
        Logger.log(itemBought.getName(), itemBought.getPrice(), this.balance);

    }

    public boolean hasEnoughMoney(BigDecimal desiredItemPrice) {
        return this.getBalance().compareTo(desiredItemPrice) >= 0;
    }

    public void giveChange(BigDecimal amountOfChange) {
        //this method is to be passed the customer's current balance
        //converts amount to number of pennies
        BigDecimal numberOfPennies = amountOfChange.multiply(BigDecimal.valueOf(100));
        BigDecimal numberOfQuarters = numberOfPennies.divide(BigDecimal.valueOf(25.00));
        BigDecimal remainderAfterQuarters = numberOfPennies.remainder(BigDecimal.valueOf(25.00));
        BigDecimal numberOfDimes = remainderAfterQuarters.divide(BigDecimal.TEN);
        BigDecimal remainderAfterDimes = remainderAfterQuarters.remainder(BigDecimal.TEN);
        BigDecimal numberOfNickels = remainderAfterDimes.divide(BigDecimal.valueOf(5.00));

        int intOfQuarters = numberOfQuarters.intValue();
        int intOfDimes = numberOfDimes.intValue();
        int intOfNickels = numberOfNickels.intValue();

        System.out.println("Your change is " + intOfQuarters +
                " Quarters, " + intOfDimes + " Dimes, and " + intOfNickels + " Nickels.");
        //set customer balance to zero
        BigDecimal cashOut = BigDecimal.ZERO;
        this.balance = cashOut;
        Logger.log("GIVE CHANGE", amountOfChange, this.balance);
    }

}
