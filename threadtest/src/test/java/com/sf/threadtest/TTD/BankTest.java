package com.sf.threadtest.TTD;

import org.junit.Test;

/**
 * Created by CodeMonkey on 2016/4/18.
 */
public class BankTest {

    @Test
    public void testTrancfer() {

        Dollar fiveDollar = Money.dollar(5, Money.DOLLAR_CURRENCY);
        Bank.transfer(fiveDollar, Money.FRANC_CURRENCY);
    }

}