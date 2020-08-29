package com.github.startup;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class CustomizeServer {

	/**
	 * CustomizeServer#main() -> CustomizeServerInitializer -> (CustomizeServerHandler + HttpServerCodec)
	 */
	public static void main(String[] args) throws InterruptedException {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();

		try {
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
					.childHandler(new CustomizeServerInitializer());

			ChannelFuture channelFuture = serverBootstrap.bind(8888).sync();
			channelFuture.channel().closeFuture().sync();
		} finally {
			System.out.println(111);
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
}
