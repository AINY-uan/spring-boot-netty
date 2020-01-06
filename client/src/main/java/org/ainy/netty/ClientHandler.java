package org.ainy.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author 阿拉丁省油的灯
 * @Date 2019-09-02 21:06
 * @Description
 */
@Slf4j
public class ClientHandler extends ChannelHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        log.info("ClientHandler Active");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {

        ByteBuf byteBuf = (ByteBuf) msg;
        log.info("ClientHandler read Message:{}", byteBuf.toString(CharsetUtil.UTF_8));
        ReferenceCountUtil.release(byteBuf);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {

        cause.printStackTrace();
        ctx.close();
    }
}
