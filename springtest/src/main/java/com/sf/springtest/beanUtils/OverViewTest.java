package com.sf.springtest.beanUtils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * Created by CodeMonkey on 2016/3/20.
 */
public class OverViewTest {

    @Test
    public void testProperty() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        Person person = new Person("zhangsan", 16, "china");
        List<String> list = new ArrayList<String>();
        list.add("zhangsan");
        list.add("zhangsan2");
        list.add("zhangsan3");

        Map<String, String> map = new HashMap<String, String>();
        map.put("name1", "zhangsan1");
        map.put("name2", "zhangsan3");
        person.setMapTest(map);
        person.setTestList(list);

        PropertyUtils.setMappedProperty(person, "mapTest(name3)", "zhangsan3");

        assertEquals("zhangsan3", BeanUtils.getMappedProperty(person, "mapTest(name3)"));
        assertEquals("zhangsan3", BeanUtils.getProperty(person, "mapTest(name3)"));
    }

    public void testNestedProperty() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        Person person = new Person("zhangsan", 16, "china");
        Person friend = new Person("friend", 16, "china");

        person.setFriend(friend);

        assertEquals("firend", BeanUtils.getProperty(person, "friend.name"));
        assertEquals("friend", BeanUtils.getNestedProperty(person, "friend.name"));
    }
}
