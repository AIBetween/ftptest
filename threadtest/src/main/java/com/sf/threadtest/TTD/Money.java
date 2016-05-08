package com.sf.threadtest.TTD;

import java.util.Optional;

/**
 * Created by CodeMonkey on 2016/4/16.
 */
public abstract class Money {

    /**
     * 代表的数额
     */
    protected double amount;
    /**
     * 货币的种类
     */
    protected String currency;

    public static final String FRANC_CURRENCY = "franc";
    public static final String DOLLAR_CURRENCY = "dollar";


    public Money(double amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public Optional<? extends Money> times(double multiplying, String currency){

        if (multiplying < 0) {

            return Optional.empty();
        }

        if (null == currency || "".equals(currency)) {

            return Optional.empty();
        }

        switch (currency) {

            case FRANC_CURRENCY:
                return Optional.of(franc(amount * multiplying, FRANC_CURRENCY));
            case DOLLAR_CURRENCY:
                return Optional.of(dollar(amount * multiplying, DOLLAR_CURRENCY));

            default:
                return Optional.empty();
        }
    }

    public static Dollar dollar(double amount, String currency) {

        return new Dollar(amount, currency);
    }

    public static Franc franc(double amount, String currency) {

        return new Franc(amount, currency);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {

            return true;
        }

        if (obj == null) {

            return false;
        }

        if (this.getClass().equals(obj.getClass())) {

            Money destMoney = (Money) obj;

            return this.getAmount() == destMoney.getAmount();
        }

        return false;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public double getAmount() {
        return amount;
    }


    private void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
