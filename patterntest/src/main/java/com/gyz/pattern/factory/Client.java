package com.gyz.pattern.factory;

/**
 * Created by CodeMonkey on 2016/4/24.
 */
public class Client {

    public static void main(String[] args) {

        YellowHuman yellowHuman = HumanFactory.createYellowHuman();
        yellowHuman.speak();
        yellowHuman.walk();
        yellowHuman.color();

        BlankHuman blankHuman = HumanFactory.createBlankHuman();
        blankHuman.speak();
        blankHuman.walk();
        blankHuman.color();
    }
}
