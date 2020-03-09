package org.ainy.config.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.EventLoop;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;
import org.ainy.util.SpringContextUtil;

import java.util.concurrent.TimeUnit;

/**
 * @author 阿拉丁省油的灯
 * @description Netty客户端配置
 * @date 2020/1/13 18:14
 */
@Slf4j
public class ClientPoHandlerProto extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf byteBuf = (ByteBuf) msg;
        log.info("ClientHandler read Message：{}", byteBuf.toString(CharsetUtil.UTF_8));
        ReferenceCountUtil.release(byteBuf);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

        NettyCliet nettyCliet = (NettyCliet) SpringContextUtil.getBean(NettyCliet.class);

        log.error("oh...no...shit......");
        // 使用过程中断线重连
        final EventLoop eventLoop = ctx.channel().eventLoop();
        eventLoop.schedule(nettyCliet::retryConnection, 1L, TimeUnit.SECONDS);
        super.channelInactive(ctx);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

        super.userEventTriggered(ctx, evt);
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state().equals(IdleState.READER_IDLE)) {
                log.info("I haven't received server push data for a long time");
            } else if (event.state().equals(IdleState.WRITER_IDLE)) {
                log.info("Data has not been sent to the server for a long time");
                // 发送心跳包
                ctx.writeAndFlush("heart$_");
            } else if (event.state().equals(IdleState.ALL_IDLE)) {
                log.info("All");
            }
        }
    }
}
