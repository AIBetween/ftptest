package com.gyz.pattern.template;

/**
 * Created by CodeMonkey on 2016/4/24.
 */
public abstract class AbstractBaseCar {

    public abstract void start();

    public abstract void stop();

    public abstract void whistle();

    /**
     * 汽车是否响铃。
     * @return 返回 true。
     */
    public boolean canWhistle() {

        return true;
    }

    public void run() {

        this.start();

        if (canWhistle()) {

            this.whistle();
        }
        this.stop();
    }
}
