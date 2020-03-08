package org.ainy.controller;

import lombok.extern.slf4j.Slf4j;
import org.ainy.config.netty.NettyCliet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author AINY_uan
 * @description Netty客户端发送数据测试
 * @date 2020-03-08 23:06
 */
@Slf4j
@RestController
@RequestMapping(value = "/ainy")
public class NettyControllerTest {

    private final NettyCliet nettyCliet;

    @Autowired
    public NettyControllerTest(NettyCliet nettyCliet) {
        this.nettyCliet = nettyCliet;
    }

    @GetMapping(value = "/netty")
    public void ainy(String param) {

        nettyCliet.sendMessage(param);
    }
}
