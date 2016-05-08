package com.gyz.pattern.factory;

/**
 * Created by CodeMonkey on 2016/4/24.
 */
public class BlankHuman implements Human {
    @Override
    public void speak() {
        System.out.println("i generally speak english");
    }

    @Override
    public void color() {
        System.out.println("i was blank color!");
    }
}
