package com.sf.threadtest.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

/**
 * Created by CodeMonkey on 2016/4/20.
 */
public class ConnectionManager {

    private static Map<String, MyConnection> connections = new HashMap<>();
    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public static MyConnection createConnection(String name) {

        WriteLock writeLock = readWriteLock.writeLock();
        writeLock.lock();

        if (!connections.containsKey(name)) {

            MyConnection myConnection = new MyConnection(name);
            connections.put(name, myConnection);
        }
        writeLock.unlock();

        return connections.get(name);
    }

    public static MyConnection getConnection(String name) {

        MyConnection result = null;
        ReadLock readLock = readWriteLock.readLock();
        readLock.lock();

        result = connections.get(name);

        readLock.unlock();

        return result;

    }
}

class MyConnection {

    private String name;

    public MyConnection(String name) {
        this.name = name;
    }
}

