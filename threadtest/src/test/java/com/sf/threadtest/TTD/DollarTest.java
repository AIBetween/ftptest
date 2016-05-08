package com.sf.threadtest.TTD;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by CodeMonkey on 2016/4/16.
 */
public class DollarTest {

    @Test
    public void testEquals() {

        Dollar dollar = Money.dollar(6, Money.DOLLAR_CURRENCY);

        assertThat(dollar.equals(Money.dollar(7, Money.DOLLAR_CURRENCY)), equalTo(false));
        assertThat(dollar.equals(Money.dollar(6, Money.DOLLAR_CURRENCY)), equalTo(true));
        assertThat(dollar.equals(Money.franc(6, Money.FRANC_CURRENCY)), equalTo(false));
        assertThat(dollar.equals(null), equalTo(false));
    }

    /**
     * 输入应该是double类型倍率。
     * 输出可能为空，这返回optinal.empty.
     */
    @Test
    public void testTimes() {

        Dollar dollar = Money.dollar(5, Money.DOLLAR_CURRENCY);


        assertThat(dollar.times(2, Money.DOLLAR_CURRENCY).get().getAmount(), equalTo(10.0));
        assertThat(dollar.times(3, Money.DOLLAR_CURRENCY).get().getAmount(), equalTo(15.0));
        assertThat(dollar.times(-1, Money.DOLLAR_CURRENCY).isPresent(), equalTo(false));

    }

    @Test
    public void testAdd() {

        Dollar fiveDollar = new Dollar(5, Money.DOLLAR_CURRENCY);

    }
}