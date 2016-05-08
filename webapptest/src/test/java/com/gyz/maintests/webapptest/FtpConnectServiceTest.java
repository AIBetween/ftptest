package com.gyz.maintests.webapptest;

import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by CodeMonkey on 2016/5/8.
 */
public class FtpConnectServiceTest {

    static ApplicationContext applicationContext;
    static {

        applicationContext = new ClassPathXmlApplicationContext("classpath:beans-core.xml");
    }
    @Test
    public void testObtainFtpService() throws Exception {

        FTPClient ftpClient = FtpConnectService.instance.obtainFtpService();

        assertThat(ftpClient, notNullValue());
    }
}