//        log.info("Server ChannelRead......");
//        log.info("Server Address:{}, Message:{}", ctx.channel().remoteAddress(), msg.toString());
//        将客户端的信息直接返回写入ctx
//        ByteBuf respByteBuf = Unpooled.copiedBuffer("Netty Server Copy$_".getBytes());
//        ctx.write(respByteBuf);
//        刷新缓存区
//        ctx.flush();
//        ByteBuf byteBuf = (ByteBuf) msg;
//        log.info("Message {}", byteBuf.toString(CharsetUtil.UTF_8));
//        ReferenceCountUtil.release(byteBuf);

package org.ainy.config.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 阿拉丁省油的灯
 * @date 2019-09-02 20:52
 * @description 服务处理
 */
@Slf4j
public class ServerHandler extends ChannelHandlerAdapter {

    private static final String HEART = "heart";

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        log.info("ChannelActive----->");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {

        ByteBuf byteBuf = (ByteBuf) msg;

        if (HEART.equals(byteBuf.toString(CharsetUtil.UTF_8))) {
            log.info("Heartbeat information sent by the client is detected......");
            ByteBuf respByteBuf = Unpooled.copiedBuffer("heart$_".getBytes());
            ctx.write(respByteBuf);
        } else {
            ByteBuf respByteBuf = Unpooled.copiedBuffer("Netty Server Copy$_".getBytes());
            ctx.write(respByteBuf);
            // 刷新缓存区
            ctx.flush();
            log.info("接收到的数据为：{}", byteBuf.toString(CharsetUtil.UTF_8));
        }
        ReferenceCountUtil.release(byteBuf);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {

        cause.printStackTrace();
        ctx.close();
    }
}
