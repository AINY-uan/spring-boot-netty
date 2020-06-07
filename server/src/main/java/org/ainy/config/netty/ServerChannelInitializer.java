package org.ainy.config.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * @author 阿拉丁省油的灯
 * @date 2019-09-02 20:51
 * @description Netty服务端初始化
 */
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel channel) {

        ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
        channel.pipeline().addLast("decoder", new DelimiterBasedFrameDecoder(4096, delimiter));
        channel.pipeline().addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));

        channel.pipeline().addLast(new ServerHandler());
    }
}
