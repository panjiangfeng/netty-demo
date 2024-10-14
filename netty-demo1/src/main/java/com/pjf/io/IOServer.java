package com.pjf.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author: Panjiangfeng
 * @CreateTime: 2024-10-11  23:22
 * @Description: IOServer
 * @Version: 1.0
 */
public class IOServer {

    public static void main(String[] args) throws Exception {

        ServerSocket serverSocket = new ServerSocket(8000);

        // 接收新连接的线程
        new Thread(() -> {
            while (true) {
                try {
                    // (1) 阻塞方法获取新连接
                    Socket socket = serverSocket.accept();

                    // (2) 为每一个新连接都创建一个新线程，负责读取数据
                    new Thread(() -> {
                        try {
                            int len;
                            byte[] data = new byte[1024];
                            InputStream inputStream = socket.getInputStream();

                            // (3) 按字节流方式读取数据
                            while ((len = inputStream.read(data)) != -1) {
                                System.out.println(new String(data, 0, len));
                            }

                        } catch (IOException e) {
                            e.printStackTrace(); // 打印异常
                        }
                    }).start();

                } catch (IOException e) {
                    e.printStackTrace(); // 打印异常
                }
            }
        }).start();
    }
}
