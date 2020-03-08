package org.ainy.config.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;
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

        log.info("Server ChannelRead......");

        ByteBuf byteBuf = (ByteBuf) msg;

        if (HEART.equals(byteBuf.toString(CharsetUtil.UTF_8))) {
            ByteBuf respByteBuf = Unpooled.copiedBuffer("heart$_".getBytes());
            ctx.write(respByteBuf);
        } else {
            ByteBuf respByteBuf = Unpooled.copiedBuffer("Netty Server Copy$_".getBytes());
            ctx.write(respByteBuf);
            // 刷新缓存区
            ctx.flush();
            log.info("接收到的数据为：{}", byteBuf.toString(CharsetUtil.UTF_8));
            // 以下方法用于处理获取到的数据
//            packageService.packsend(byteBuf.toString(CharsetUtil.UTF_8));
//            ReferenceCountUtil.release(byteBuf);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {

        cause.printStackTrace();
        ctx.close();
    }
}