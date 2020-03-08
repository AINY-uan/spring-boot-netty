package org.ainy.config.netty;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoop;
import lombok.extern.slf4j.Slf4j;
import org.ainy.util.SpringContextUtil;

import java.util.concurrent.TimeUnit;

/**
 * @author 阿拉丁省油的灯
 * @date 2019-09-02 21:09
 * @description 负责监听启动时连接失败，重新连接功能
 */
@Slf4j
public class ConnectionListener implements ChannelFutureListener {

    @Override
    public void operationComplete(ChannelFuture channelFuture) throws Exception {

        if (!channelFuture.isSuccess()) {

            NettyCliet nettyCliet = (NettyCliet) SpringContextUtil.getBean(NettyCliet.class);

            final EventLoop loop = channelFuture.channel().eventLoop();
            loop.schedule(() -> {
                log.error("oh...no...shit");
                nettyCliet.retryConnection();
            }, 1L, TimeUnit.SECONDS);
        } else {
            log.info("oh...yeah...success");
        }
    }
}
