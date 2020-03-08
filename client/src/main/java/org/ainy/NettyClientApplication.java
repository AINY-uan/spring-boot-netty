package org.ainy;

import org.ainy.util.SpringContextUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @author 阿拉丁省油的灯
 * @date 2020-01-06 22:35
 * @description Netty客户端启动类
 */
@SpringBootApplication
public class NettyClientApplication {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(NettyClientApplication.class, args);

        SpringContextUtil.setApplicationContext(context);
    }
}
