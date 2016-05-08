package com.gyz.pattern.factory;

/**
 * Created by CodeMonkey on 2016/4/24.
 */
public class HumanFactory {

    public static YellowHuman createYellowHuman() {

        return new YellowHuman();
    }

    public static BlankHuman createBlankHuman() {

        return new BlankHuman();
    }
}
