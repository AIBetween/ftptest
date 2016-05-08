package com.sf.springtest.beanUtils;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by CodeMonkey on 2016/3/20.
 */
public class beanUtilsTest {

    @Test
    public void testClone() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {

        Person person1 = new Person();
        BeanUtils.setProperty(person1, "name", "zhangsan");
        person1.setAge(12);
        person1.setAddress("china");

        Person person2 = (Person) BeanUtils.cloneBean(person1);

        assertEquals(false, person1 == person2);
        assertEquals("zhangsan", person2.getName());
        assertEquals(12, person2.getAge());
        assertEquals("china", person2.getAddress());
    }

    @Test
    public void testDesc() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        Person person = new Person("zhangsan", 16, "china");

        Map descMap = BeanUtils.describe(person);
        System.out.println(descMap);
    }

    @Test
    public void testGetIndex() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        List<String> list = new ArrayList<String>();
        list.add("zhangsan");
        list.add("lisi");
        list.add("wangwu");
        list.add("zhaoliu");

        Map<String, String> map = new HashMap<String, String>();
        map.put("username", "zhangsan");
        map.put("age", "16");

        Person person = new Person("zhangsan", 16, "china");
        person.setTestList(list);
        person.setMapTest(map);

        String result = BeanUtils.getIndexedProperty(person, "testList[1]");
        assertEquals("lisi", result);

        String mapResult = BeanUtils.getMappedProperty(person, "mapTest", "username");
        assertEquals("zhangsan", mapResult);
    }


    @Test
    public void testPopulate() throws InvocationTargetException, IllegalAccessException {

        Person person = new Person();

        Map<String, Object> parasMap = new HashMap<String, Object>();
        parasMap.put("name", "zhangsan");
        parasMap.put("age", 16);
        parasMap.put("address", "china");

        BeanUtils.populate(person, parasMap);

        assertEquals("zhangsan", person.getName());
        assertEquals(16, person.getAge());
        assertEquals("china", person.getAddress());
    }

}
