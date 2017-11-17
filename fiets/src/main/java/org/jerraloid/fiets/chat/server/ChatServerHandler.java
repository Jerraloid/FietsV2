package org.jerraloid.fiets.chat.server;

import java.nio.charset.Charset;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

public class ChatServerHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		try {
			Charset utf8 = CharsetUtil.UTF_8;
			String in = ((ByteBuf)msg).toString(utf8);
			
			System.out.println(in);
		}
		finally {
			
		}
	}
}
