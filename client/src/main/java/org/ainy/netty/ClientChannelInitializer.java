package org.ainy.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * @Author 阿拉丁省油的灯
 * @Date 2019-09-02 21:07
 * @Description
 */
public class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {

    protected void initChannel(SocketChannel channel) {

        ChannelPipeline p = channel.pipeline();

//        p.addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
//        p.addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));

        ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
        channel.pipeline().addLast("decoder", new DelimiterBasedFrameDecoder(4096, delimiter));
        channel.pipeline().addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));

        p.addLast(new ClientHandler());
    }
}
