package org.jerraloid.fiets.chat.server;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class ChatServer {
	
	final int PORT;
	
	public ChatServer(int port) {
		PORT = port;
		start();
	}
	
	public void start() {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		
		try {
			new ServerBootstrap()
				.group(bossGroup, workerGroup)
				.channel(NioServerSocketChannel.class)
				.childHandler(new ChatServerInit())
				.bind(PORT).sync().channel().closeFuture().sync();
		}
		catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}

}
