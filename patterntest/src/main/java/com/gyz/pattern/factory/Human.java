package com.gyz.pattern.factory;

/**
 * Created by CodeMonkey on 2016/4/24.
 */
public interface Human {

    public void speak();

    default public void walk(){

        System.out.println("i can walk!");
    }

    public void color();
}
