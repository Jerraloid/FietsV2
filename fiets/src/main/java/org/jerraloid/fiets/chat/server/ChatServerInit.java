package org.jerraloid.fiets.chat.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;

public class ChatServerInit extends ChannelInitializer<Channel> {

	@Override
	protected void initChannel(Channel ch) throws Exception {
		ch.pipeline().addLast(new ChatServerHandler());
		
	}

}
