package com.datacomo.wins.base.socket.im;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.filter.codec.demux.MessageEncoder;

/**
 * 
* @ClassName: IMMessageEncoder 
* @Description: IM协议包体封装类
* @author peisong
* @date 2013-8-2 下午1:49:04 
*
 */
public class IMMessageEncoder implements MessageEncoder<IMDataPacket> {

	@Override
	public void encode(IoSession session, IMDataPacket data,
			ProtocolEncoderOutput out) throws Exception {
		IoBuffer buf = IoBuffer.allocate(100).setAutoExpand(true);
		buf.put(data.getDataPacketBytes());
		buf.flip();
		out.write(buf);
		out.flush();
	}

}
