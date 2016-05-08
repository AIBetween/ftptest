package com.gyz.maintests.webapptest;

import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by CodeMonkey on 2016/5/8.
 */
public class FtpFileService implements InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(FtpFileService.class);
    public static final String TEM_DIRECTORY = "/jax1";
    private FtpConnectService ftpConnectService = FtpConnectService.instance;
    public static FtpFileService instance;

    // TODO: 2016/5/8 需要持有config的配置。
    // TODO: 2016/5/8 需要将具体的文件名设置为config中的。
    @Override
    public void afterPropertiesSet() throws Exception {

        instance = this;

        createFileStoreDirectory(TEM_DIRECTORY);
    }

    private void createFileStoreDirectory(String storeDirectory) {

        try {
            boolean createResult = ftpConnectService.obtainFtpService().makeDirectory(storeDirectory);
            System.out.println("create " + createResult+"");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean upLoadFile(File upLoadFile) {

        if (!upLoadFile.exists()) {

            logger.error("file not exist when upLoad ftp! file:{}", upLoadFile.getAbsolutePath());
            return false;
        }

        FTPClient ftpClient = ftpConnectService.obtainFtpService();
        try (FileInputStream fileInputStream = new FileInputStream(upLoadFile);) {

            ftpClient.changeWorkingDirectory(TEM_DIRECTORY);
            ftpClient.storeFile(upLoadFile.getName(), fileInputStream);
            return true;

        } catch (IOException e) {
            logger.error("ftp change directory error! {}", TEM_DIRECTORY);
        }

        return false;
    }
}
