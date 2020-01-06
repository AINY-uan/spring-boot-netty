package org.ainy.schedule;

import lombok.extern.slf4j.Slf4j;
import org.ainy.netty.NettyCliet;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author 阿拉丁省油的灯
 * @Date 2019-09-02 21:10
 * @Description
 */
@Slf4j
@Component
public class Test {

    @Scheduled(cron = "*/1 * * * * ?")
    public void ainy() {

        NettyCliet.sendMessage("从前有座灵剑山");
    }
}
