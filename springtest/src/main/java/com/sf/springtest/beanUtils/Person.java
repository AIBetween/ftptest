package com.sf.springtest.beanUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by CodeMonkey on 2016/3/20.
 */
public class Person {

    private String name;
    private int age;
    private String address;
    private List<String> testList;
    private Map<String, String> mapTest;
    private Person friend;

    public Person getFriend() {
        return friend;
    }

    public void setFriend(Person friend) {
        this.friend = friend;
    }

    public List<String> getTestList() {
        return testList;
    }

    public void setTestList(List<String> testList) {
        this.testList = testList;
    }

    public Map<String, String> getMapTest() {
        return mapTest;
    }

    public void setMapTest(Map<String, String> mapTest) {
        this.mapTest = mapTest;
    }

    public Person() {
    }

    public Person(String name, int age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return String.format("Person[name:%s, age:%d, address:%s]", name, age, address);
    }
}
