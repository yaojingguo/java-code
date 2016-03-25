package netty.time;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class TimeEncoder extends MessageToByteEncoder<UnixTime> {
  @Override
  protected void encode(ChannelHandlerContext ctx, UnixTime msg, ByteBuf out) {
    System.out.printf("time: %ld\n", msg.value());
    out.writeInt((int) msg.value());
  }
}
