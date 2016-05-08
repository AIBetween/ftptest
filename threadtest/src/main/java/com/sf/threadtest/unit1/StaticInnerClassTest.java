package com.sf.threadtest.unit1;

/**
 * Created by CodeMonkey on 2016/4/4.
 */
public class StaticInnerClassTest {

    public static class InnerClass {

        private String username;
        private String address;


        public void methodA() {

            System.out.println("print methodA");
        }
    }

    public static void main(String[] args) {

        InnerClass innerClass = new StaticInnerClassTest.InnerClass();

        innerClass.methodA();
    }


}
