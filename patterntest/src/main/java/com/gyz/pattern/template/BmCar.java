package com.gyz.pattern.template;

/**
 * Created by CodeMonkey on 2016/4/24.
 */
public class BmCar extends AbstractBaseCar {
    @Override
    public void start() {
        System.out.println("宝马车起步");
    }

    @Override
    public void stop() {

        System.out.println("宝马车停止");
    }

    @Override
    public void whistle() {

        System.out.println("宝马车响铃");
    }

    @Override
    public boolean canWhistle() {

        return false;
    }
}
