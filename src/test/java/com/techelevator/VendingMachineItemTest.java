package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class VendingMachineItemTest {

    @Test
    public void vendingMachineItemConstructor_is_working_properly(){
        String[] testArr = new String[] {"A1", "Potato Crisps", "3.05", "Chip"};
        VendingMachineItem sut = new VendingMachineItem(testArr);
        Assert.assertEquals(sut.getItemSlot(), "A1");
        Assert.assertEquals(sut.getName(), "Potato Crisps");
        Assert.assertEquals(sut.getPrice(), BigDecimal.valueOf(3.05));
        Assert.assertEquals(sut.getItemType(), "Chip");
    }

}
