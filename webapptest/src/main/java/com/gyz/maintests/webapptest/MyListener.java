package com.gyz.maintests.webapptest;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.*;

/**
 * Created by CodeMonkey on 2016/5/8.
 */
public class MyListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        ServletContext servletContext = servletContextEvent.getServletContext();
        String temBasePath = servletContext.getRealPath("/temfile");
        String filePath = temBasePath + "/temFile.txt";
        File file = new File(filePath.replace("\\", "/"));
        if (!file.exists()) {

            if (!file.getParentFile().exists()) {

                boolean parentMk = file.getParentFile().mkdirs();
                System.out.println("parentMk " + parentMk);


            }
            System.out.println(file.exists());

            try {
                boolean newFile = file.createNewFile();
                System.out.println(newFile);
            } catch (IOException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();

            }

            StringBuffer stringBuffer = new StringBuffer("nihao zhangsan")
                    .append("nihao lisi")
                    .append("nihao wangwu")
                    .append("nihao zhaoliu");

            try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));){

                bufferedWriter.write(stringBuffer.toString());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
