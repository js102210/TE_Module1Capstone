package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

public class InventoryTest {


    @Test
    public void prepareItem_is_working_properly(){
        String[] desiredArr = new String[] {"A1", "Potato Crisps", "3.05", "Chip"};
        String itemString = "A1|Potato Crisps|3.05|Chip";

        String[] sut = Inventory.prepareItem(itemString);

        Assert.assertEquals(sut, desiredArr);
    }

}
