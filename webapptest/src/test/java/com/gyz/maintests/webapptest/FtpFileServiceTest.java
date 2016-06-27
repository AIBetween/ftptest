package com.gyz.maintests.webapptest;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.IOException;

/**
 * Created by CodeMonkey on 2016/5/8.
 */
public class FtpFileServiceTest {

    static ApplicationContext applicationContext;

    static {

        applicationContext = new ClassPathXmlApplicationContext("classpath:beans-core.xml");
    }

    @Test
    public void testUpLoadFile() throws Exception {

        FtpFileService instance = FtpFileService.instance;

        new Thread(() -> {

            try {
                boolean result = instance.upLoadFile(new File("E:\\nihao\\2016-05-09_17.23.05_6f0acf3f-647f-4285-9078-a7bfadd448c6.txt"));
                System.out.println(false);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("");
            System.out.println("nihao zhangsan");
        }).start();

    }
}