package com.pjf.io;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;

/**
 * @Author: Panjiangfeng
 * @CreateTime: 2024-10-11  23:23
 * @Description: IOClient
 * @Version: 1.0
 */
public class IOClient {
    public static void main(String[] args) {

        new Thread(() -> {

            try {
                Socket socket = new Socket("127.0.0.1", 8000);

                while (true) {
                    try {
                        // 发送消息到服务端
                        socket.getOutputStream().write((new Date() + ": hello world").getBytes());

                        // 休眠2秒
                        Thread.sleep(2000);

                    } catch (Exception e) {
                        e.printStackTrace(); // 打印异常
                    }
                }

            } catch (IOException e) {
                e.printStackTrace(); // 打印异常
            }

        }).start();
    }

}
