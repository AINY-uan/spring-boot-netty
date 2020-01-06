package org.ainy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author 阿拉丁省油的灯
 * @Date 2020-01-06 22:35
 * @Description Netty客户端启动类
 */
@SpringBootApplication
@EnableScheduling
public class NettyClientApplication {

    public static void main(String[] args) {

        SpringApplication.run(NettyClientApplication.class, args);
    }
}
