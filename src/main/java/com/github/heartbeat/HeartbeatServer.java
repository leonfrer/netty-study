package com.github.heartbeat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class HeartbeatServer {

	public static void main(String[] args) throws InterruptedException {
		EventLoopGroup bossLoopGroup = new NioEventLoopGroup();
		EventLoopGroup employeeLoopGroup = new NioEventLoopGroup();

		try {
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			serverBootstrap.group(bossLoopGroup, employeeLoopGroup).channel(NioServerSocketChannel.class)
					.handler(new LoggingHandler(LogLevel.INFO))
					.childHandler(new HeartbeatInitializer());

			ChannelFuture channelFuture = serverBootstrap.bind(8889).sync();
			channelFuture.channel().closeFuture().sync();
		} finally {
			bossLoopGroup.shutdownGracefully();
			employeeLoopGroup.shutdownGracefully();
		}
	}
}
