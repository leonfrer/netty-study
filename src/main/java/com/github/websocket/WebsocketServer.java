package com.github.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * when client out of network directly, server cannot 'feel' it
 * cause this server is lack of heartbeat
 */
public class WebsocketServer {

	public static void main(String[] args) throws InterruptedException {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup employeeGroup = new NioEventLoopGroup();

		try {
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			serverBootstrap.group(bossGroup, employeeGroup).channel(NioServerSocketChannel.class)
					.childHandler(new WebsocketChannelInitializer());

			ChannelFuture channelFuture = serverBootstrap.bind(new InetSocketAddress(8889)).sync();
			channelFuture.channel().closeFuture().sync();
		} finally {
			bossGroup.shutdownGracefully();
			employeeGroup.shutdownGracefully();
		}
	}
}
