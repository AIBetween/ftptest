package com.sf.springtest.beanUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by CodeMonkey on 2016/4/16.
 */
public class MyTest {


}

class Student {

    private String name;
    private int age;
    private List<String> stdudents = new ArrayList<String>();
    private Map<String, String> map = new HashMap<String, String>();


    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {

            String zhangsan = new String("zhangsan");

        }
    }
    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<String> getStdudents() {
        return stdudents;
    }

    public void setStdudents(List<String> stdudents) {
        this.stdudents = stdudents;
    }
}