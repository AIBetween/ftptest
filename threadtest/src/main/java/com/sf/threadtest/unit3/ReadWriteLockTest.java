package com.sf.threadtest.unit3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by CodeMonkey on 2016/4/10.
 */
public class ReadWriteLockTest {


    public static void main(String[] args) {

        ReadWriterService readWriterService = new ReadWriterService();
        for (int i = 0; i < 10; i++) {

            Thread threadRead = new Thread(readWriterService::read, "readThread" + i);
            final int finalI = i;
            Thread threadWriter = new Thread(() -> {
                readWriterService.write(finalI + "");
            }, "writerThread" + i);

            threadRead.start();
            threadWriter.start();
        }
    }
}

class ReadWriterService {

    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private List<String> strList = new ArrayList<>();

    public String read() {

        readWriteLock.readLock().lock();

        try {

            System.out.println("get read lock! " + Thread.currentThread().getName() + System.currentTimeMillis());
            Thread.sleep(1000);
            if (strList.size() > 0) {

                return strList.get(strList.size() - 1);
            }else {

                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();

            return null;
        } finally {

            readWriteLock.readLock().unlock();
        }

    }

    public void write(String data) {

        readWriteLock.writeLock().lock();

        try {
            System.out.println(String.format("%s write %d"
                , Thread.currentThread().getName()
                , System.currentTimeMillis()));
            Thread.sleep(1000);
            strList.add(data);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            readWriteLock.writeLock().unlock();
        }
    }
}
