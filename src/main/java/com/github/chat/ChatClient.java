package com.github.chat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.internal.StringUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ChatClient {

	public static void main(String[] args) throws InterruptedException, IOException {
		EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

		try {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class)
					.handler(new ChatClientInitializer());

			ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8889).sync();
			Channel channel = channelFuture.channel();

			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			for (; ; ) {
				String s = br.readLine();
				if (!StringUtil.isNullOrEmpty(s)) {
					channel.writeAndFlush(s + "\n");
				}
			}
		} finally {
			eventLoopGroup.shutdownGracefully();
		}
	}
}
