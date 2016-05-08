package com.gyz.maintests.webapptest;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;

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

        instance.upLoadFile(new File("h:/nihao.txt"));

    }
}