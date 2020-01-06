package org.ainy.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Author 阿拉丁省油的灯
 * @Date 2019-09-02 21:09
 * @Description
 */
@Component
public class NettyCliet {

    private static final String HOST = "127.0.0.1";
    private static final int PORT = 10240;

    private static ChannelFuture future;

    @PostConstruct
    public void init() {

        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ClientChannelInitializer());

            future = b.connect(HOST, PORT).sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sendMessage(String msg) {

        String reqMsg = msg + "$_";

        future.channel().writeAndFlush(Unpooled.copiedBuffer(reqMsg, CharsetUtil.UTF_8));
    }
}
