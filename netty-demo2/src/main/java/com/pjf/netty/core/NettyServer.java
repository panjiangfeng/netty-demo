package com.pjf.netty.core;

import com.pjf.netty.handle.FirstServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * @Author: pjf
 * @CreateTime: 2024-10-14  15:50
 * @Description: TODO
 * @Version: 1.0
 */
public class NettyServer {

    public static void main(String[] args) {

        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        serverBootstrap
                .group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .handler(new ChannelInitializer<NioServerSocketChannel>() {
                    @Override
                    protected void initChannel(NioServerSocketChannel ch) {
                        System.out.println("服务端启动中");
                    }
                })
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) {
                        ch.pipeline().addFirst(new FirstServerHandler());
                        System.out.println("获取到一个连接");
                    }
                });
        serverBootstrap
                //为服务端Channel创建自定义属性
                .attr(AttributeKey.newInstance("serverName"), "nettyServer")
                //为每个连接创建自定义属性
                .childAttr(AttributeKey.newInstance("clientKey"), "clientValue")
                //为服务端Channel设置一些TCP参数
                .option(ChannelOption.SO_BACKLOG, 1024)
                //为每个连接都设置一些TCP参数
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .bind(8808);
        //bind(serverBootstrap, 1000);
    }

    private static void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) {
                if (future.isSuccess()) {
                    System.out.println("端口［" + port + "］绑定成功！");
                } else {
                    System.err.println("端口［" + port + "］绑定失败！");
                    bind(serverBootstrap, port + 1);
                }
            }
        });
    }

}
