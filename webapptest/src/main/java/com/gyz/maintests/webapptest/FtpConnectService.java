package com.gyz.maintests.webapptest;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import java.io.IOException;
import java.util.Optional;

/**
 * Created by CodeMonkey on 2016/5/8.
 */
public class FtpConnectService implements InitializingBean{

    private static final Logger logger = LoggerFactory.getLogger(FtpConnectService.class);
    private FTPClient indoorService;
    public static final String FTP_ADDRESS = "009.3vftp.com";
    public static final int FTP_PORT = 21;
    public static final String USERNAME = "aibetween";
    public static final String PASSWORD = "yao63912";
    public static final int MAX_TRY_TIME_FAIL = 3;

    public static FtpConnectService instance;

    /**
     * 获取ftp 客户端的链接。
     * @return 一个获取的客户端连接。
     */
    public FTPClient obtainFtpService() {

        if (null == indoorService || !indoorService.isConnected()) {

            Optional<FTPClient> ftpClientOptional = connectFtpServerIfFailTrySomeTime();
            if (ftpClientOptional.isPresent()) {

                indoorService = ftpClientOptional.get();
            }else {

                //// TODO: 2016/5/8  异常的处理。
                logger.error("can't get FTP service!");
            }
        }

        return indoorService;
    }

    /**
     * 获取 ftp 链接
     * @return 返回optional包装的ftpClinet 链接。可能是 optional.empty.
     */
    private Optional<FTPClient> connectFtpServer() {

        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(FTP_ADDRESS, FTP_PORT);
            ftpClient.login(USERNAME, PASSWORD);
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            int replyCode = ftpClient.getReplyCode();

            if (!FTPReply.isPositiveCompletion(replyCode)) {

                ftpClient.disconnect();
                return Optional.empty();
            }else {

                return Optional.of(ftpClient);
            }

        } catch (IOException e) {

            logger.error("connect ftp server error!", e);
            return Optional.empty();
        }
    }

    /**
     * 获取ftp server 服务，如果失败尝试3 次获取。
     * @return 由Optional 包装的FtpClient.注意检查是否获取成功。
     */
    private Optional<FTPClient> connectFtpServerIfFailTrySomeTime() {

        Optional<FTPClient> ftpClientOptional;
        int haveTry = 0;

        //总共会尝试4次链接。首次链接 和 3次失败链接。
        do {
            ftpClientOptional = connectFtpServer();
            haveTry++;

            if (haveTry != 1) {

                logger.error("connect ftp server error, have try {} time!", haveTry);
            }
        } while (!ftpClientOptional.isPresent() && haveTry < MAX_TRY_TIME_FAIL);

        if (!ftpClientOptional.isPresent()) {

            logger.error("can not connect server, please check!, address{}, port{}, usename{}, password{}"
                    , FTP_ADDRESS, FTP_PORT, USERNAME, PASSWORD);
        }

        return ftpClientOptional;
    }


    @Override
    public void afterPropertiesSet() throws Exception {

        instance = this;
        Optional<FTPClient> ftpClientOptional = connectFtpServerIfFailTrySomeTime();
        if (ftpClientOptional.isPresent()) {

            indoorService = ftpClientOptional.get();
        }else {

            logger.error("In system Init, can not connect server!");
        }
    }

    /**
     * 用于启动程序时候检查。
     * @return 如果得到了ftpServer，则说明启动成功，否则失败。
     */
    public boolean isSuccessfulStarUp() {

        return null != indoorService;
    }
}
