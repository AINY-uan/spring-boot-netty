package org.ainy.config.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author 阿拉丁省油的灯
 * @date 2019-09-02 21:09
 * @description
 */
@Slf4j
@Component
public class NettyClient {

    @Value("${netty.url}")
    private String host;
    @Value("${netty.port}")
    private int port;

    private static ChannelFuture future;

    /**
     * 启动时连接
     */
    @PostConstruct
    public void init() {

        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ClientChannelInitializer());

            future = b.connect(host, port);

            future.addListener(new ConnectionListener());

        } catch (Exception e) {
            log.error("[连接出现错误]", e );
        }
    }

    /**
     * 断线重连
     */
    public void retryConnection() {

        log.info("oh...retry...");

        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ClientChannelInitializer());

            future = b.connect(host, port);

            future.addListener(new ConnectionListener());

        } catch (Exception e) {
            log.error("[重新连接出现错误]", e );
        }
    }

    public void sendMessage(String msg) {

        String reqMsg = msg + "$_";

        future.channel().writeAndFlush(Unpooled.copiedBuffer(reqMsg, CharsetUtil.UTF_8));
    }
}
