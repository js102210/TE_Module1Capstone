package com.techelevator;

import com.techelevator.view.Customer;
import org.junit.Test;

import java.math.BigDecimal;

public class GiveChangeTest {
    @Test
    public void giveChange_returns_4_quarters_from_dollar(){
        Customer sut = new Customer();
        sut.giveChange(BigDecimal.valueOf(100.00));
    }
    @Test
    public void giveChange_returns_4_quarters_one_dime_one_nickel_from_dollar_fifteen(){
        Customer sut = new Customer();
        sut.giveChange(BigDecimal.valueOf(115.00));
    }




}
