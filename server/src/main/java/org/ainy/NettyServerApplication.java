package org.ainy;

import org.ainy.service.netty.NettyServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetSocketAddress;

/**
 * @Author 阿拉丁省油的灯
 * @Date 2020-01-06 22:24
 * @Description Netty服务端启动类
 */
@SpringBootApplication
public class NettyServerApplication implements CommandLineRunner {

    private final NettyServer nettyServer;

    @Autowired
    public NettyServerApplication(NettyServer nettyServer) {
        this.nettyServer = nettyServer;
    }

    @Value("${netty.port}")
    private int port;

    @Value("${netty.url}")
    private String url;


    public static void main(String[] args) {

        SpringApplication.run(NettyServerApplication.class, args);
    }

    @Override
    public void run(String... args) {

        InetSocketAddress address = new InetSocketAddress(url, port);
        nettyServer.start(address);
    }
}
